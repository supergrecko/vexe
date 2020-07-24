package dev.supergrecko.vexe.llvm.ir

import dev.supergrecko.vexe.llvm.internal.contracts.PointerIterator
import dev.supergrecko.vexe.llvm.internal.contracts.Unreachable
import dev.supergrecko.vexe.llvm.internal.contracts.Validatable
import dev.supergrecko.vexe.llvm.internal.util.fromLLVMBool
import dev.supergrecko.vexe.llvm.ir.values.traits.DebugLocationValue
import org.bytedeco.javacpp.SizeTPointer
import org.bytedeco.llvm.LLVM.LLVMValueRef
import org.bytedeco.llvm.global.LLVM

public open class Instruction internal constructor() : Value(),
    DebugLocationValue, Validatable, Cloneable {
    public override var valid = true

    public constructor(llvmRef: LLVMValueRef) : this() {
        ref = llvmRef
    }

    //region Core::Instructions
    /**
     * Determine if this instruction has metadata
     *
     * @see LLVM.LLVMHasMetadata
     */
    public fun hasMetadata(): Boolean {
        return LLVM.LLVMHasMetadata(ref).fromLLVMBool()
    }

    /**
     * Get the metadata for this instruction
     *
     * If the instruction does not have metadata, an exception will be thrown
     *
     * @see LLVM.LLVMGetMetadata
     */
    public fun getMetadata(kind: Int): Value? {
        val value = LLVM.LLVMGetMetadata(ref, kind)

        return value?.let { Value(it) }
    }

    /**
     * Get the metadata for this instruction
     *
     * This function converts the [kind] to the integer kind. This requires a
     * context. You can pass a custom context via the [context] argument. By
     * default, the context the instruction was created in will be used.
     *
     * @see LLVM.LLVMGetMetadata
     */
    public fun getMetadata(
        kind: String,
        context: Context = getContext()
    ): Value? {
        val kindId = context.getMetadataKindId(kind)

        return getMetadata(kindId)
    }

    /**
     * Set the metadata for this instruction
     *
     * This function uses numeric metadata ids. If you prefer to use string
     * ids, use the overload for [String]
     **
     * @see LLVM.LLVMSetMetadata
     */
    public fun setMetadata(kind: Int, metadata: Value) {
        LLVM.LLVMSetMetadata(ref, kind, metadata.ref)
    }

    /**
     * Set the metadata for this instruction
     *
     * This function converts the [kind] to the integer kind. This requires a
     * context. You can pass a custom context via the [context] argument. By
     * default, the context the instruction was created in will be used.
     *
     * @see LLVM.LLVMGetMDKindIDInContext
     * @see LLVM.LLVMSetMetadata
     */
    public fun setMetadata(
        kind: String,
        metadata: Value,
        context: Context = getContext()
    ) {
        val kindId = context.getMetadataKindId(kind)

        setMetadata(kindId, metadata)
    }

    /**
     * Get all the metadata for the instruction apart from debug location
     * metadata
     *
     * @see LLVM.LLVMInstructionGetAllMetadataOtherThanDebugLoc
     */
    public fun getAllMetadataExceptDebugLocations(): MetadataEntries {
        val size = SizeTPointer(0)
        val entries = LLVM.LLVMInstructionGetAllMetadataOtherThanDebugLoc(
            ref,
            size
        )

        return MetadataEntries(entries, size)
    }

    /**
     * Get the [BasicBlock] this instruction lives inside
     *
     * @see LLVM.LLVMGetInstructionParent
     */
    public fun getInstructionBlock(): BasicBlock? {
        val bb = LLVM.LLVMGetInstructionParent(ref)

        return bb?.let { BasicBlock(it) }
    }

    /**
     * Removes the instruction from the basic block it resides in
     *
     * @see LLVM.LLVMInstructionRemoveFromParent
     */
    public fun remove() {
        require(getInstructionBlock() != null) { "This block has no parent" }

        LLVM.LLVMInstructionRemoveFromParent(ref)
    }

    /**
     * Removes the instruction from the basic block it resides in and deletes
     * the reference
     *
     * @see LLVM.LLVMInstructionEraseFromParent
     */
    public fun delete() {
        require(getInstructionBlock() != null) { "This block has no parent" }
        require(valid) { "This instruction has already been deleted" }

        valid = false

        LLVM.LLVMInstructionEraseFromParent(ref)
    }

    /**
     * Get the opcode for this instruction
     *
     * @see LLVM.LLVMGetInstructionOpcode
     */
    public fun getOpcode(): Opcode {
        val opcode = LLVM.LLVMGetInstructionOpcode(ref)

        return Opcode.values()
            .firstOrNull { it.value == opcode }
            ?: throw Unreachable()
    }

    /**
     * Clone the opcode
     *
     * The clone does not have a basic block attached and it does not have a
     * name either
     *
     * @see LLVM.LLVMInstructionClone
     */
    public override fun clone(): Instruction {
        val clone = LLVM.LLVMInstructionClone(ref)

        return Instruction(clone)
    }

    /**
     * Determine if this instruction is a terminator instruction
     *
     * @see LLVM.LLVMIsATerminatorInst
     */
    public fun isTerminator(): Boolean {
        val inst = LLVM.LLVMIsATerminatorInst(ref)

        return inst != null
    }
    //endregion Core::Instructions

    //region Core::Instructions::Terminators
    // TODO: Move into Terminator interface
    /**
     * Get the number of successors that this terminator has
     *
     * @see LLVM.LLVMGetNumSuccessors
     */
    public fun getSuccessorCount(): Int {
        require(isTerminator()) {
            "This instruction is not a terminator"
        }

        return LLVM.LLVMGetNumSuccessors(ref)
    }

    /**
     * Get a successor at [index]
     *
     * @see LLVM.LLVMGetSuccessor
     */
    public fun getSuccessor(index: Int): BasicBlock {
        require(isTerminator()) {
            "This instruction is not a terminator"
        }
        require(index < getSuccessorCount()) {
            "Out of bounds index. Index: $index, Count: ${getSuccessorCount()}"
        }

        val bb = LLVM.LLVMGetSuccessor(ref, index)

        return BasicBlock(bb)
    }

    /**
     * Set a successor at [index]
     *
     * @see LLVM.LLVMSetSuccessor
     */
    public fun setSuccessor(index: Int, block: BasicBlock) {
        require(isTerminator()) {
            "This instruction is not a terminator"
        }

        LLVM.LLVMSetSuccessor(ref, index, block.ref)
    }
    //endregion Core::Instructions::Terminators

    /**
     * Class to perform iteration over instructions
     *
     * @see [PointerIterator]
     */
    public class Iterator(ref: LLVMValueRef) :
        PointerIterator<Instruction, LLVMValueRef>(
            start = ref,
            yieldNext = { LLVM.LLVMGetNextInstruction(it) },
            apply = { Instruction(it) }
        )
}
