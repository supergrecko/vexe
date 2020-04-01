package dev.supergrecko.kllvm.core.types

import dev.supergrecko.kllvm.annotations.Shared
import dev.supergrecko.kllvm.core.enumerations.TypeKind
import dev.supergrecko.kllvm.core.typedefs.Type
import dev.supergrecko.kllvm.utils.iterateIntoType
import org.bytedeco.javacpp.PointerPointer
import org.bytedeco.llvm.LLVM.LLVMTypeRef
import org.bytedeco.llvm.global.LLVM

public class ArrayType internal constructor() : Type() {
    public constructor(llvmType: LLVMTypeRef) : this() {
        ref = llvmType
        requireKind(TypeKind.Array)
    }

    /**
     * Create an array type
     *
     * Constructs an array of type [type] with size [size].
     */
    public constructor(type: Type, size: Int) : this() {
        require(size >= 0) { "Cannot make array of negative size" }

        ref = LLVM.LLVMArrayType(type.ref, size)
    }

    //region Core::Types::SequentialTypes
    /**
     * Returns the amount of elements contained in this type
     *
     * This is shared with [ArrayType], [VectorType], [PointerType]
     */
    public fun getElementCount(): Int {
        return LLVM.LLVMGetArrayLength(ref)
    }

    /**
     * Returns type's subtypes
     *
     * This is shared with [ArrayType], [VectorType], [PointerType]
     */
    @Shared
    public fun getSubtypes(): List<Type> {
        val dest = PointerPointer<LLVMTypeRef>(getElementCount().toLong())
        LLVM.LLVMGetSubtypes(ref, dest)

        return dest.iterateIntoType { Type(it) }
    }

    /**
     * Obtain the type of elements within a sequential type
     *
     * This is shared with [ArrayType], [VectorType], [PointerType]
     */
    @Shared
    public fun getElementType(): Type {
        val type = LLVM.LLVMGetElementType(ref)

        return Type(type)
    }
    //endregion Core::Types::SequentialTypes
}