package io.vexelabs.bitbuilder.llvm.ir

/**
 * This file contains a bunch of enum types LLVM uses to work with its IR
 */

import io.vexelabs.bitbuilder.llvm.internal.contracts.OrderedEnum
import org.bytedeco.llvm.global.LLVM

public enum class ThreadLocalMode(public override val value: Int) :
    OrderedEnum<Int> {
    NotThreadLocal(LLVM.LLVMNotThreadLocal),
    GeneralDynamicTLSModel(LLVM.LLVMGeneralDynamicTLSModel),
    LocalDynamicTLSModel(LLVM.LLVMLocalDynamicTLSModel),
    InitialExecTLSModel(LLVM.LLVMInitialExecTLSModel),
    LocalExecTLSModel(LLVM.LLVMLocalExecTLSModel)
}

public enum class CallConvention(public override val value: Int) :
    OrderedEnum<Int> {
    CCall(LLVM.LLVMCCallConv),
    FastCall(LLVM.LLVMFastCallConv),
    ColdCall(LLVM.LLVMColdCallConv),
    GHCCall(LLVM.LLVMGHCCallConv),
    HiPECall(LLVM.LLVMHiPECallConv),
    WebKitJSCall(LLVM.LLVMWebKitJSCallConv),
    AnyRegCall(LLVM.LLVMAnyRegCallConv),
    PreserveMostCall(LLVM.LLVMPreserveMostCallConv),
    PreserveAllCall(LLVM.LLVMPreserveAllCallConv),
    SwiftCall(LLVM.LLVMSwiftCallConv),
    CXXFASTTLSCall(LLVM.LLVMCXXFASTTLSCallConv),
    X86StdcallCall(LLVM.LLVMX86StdcallCallConv),
    X86FastcallCall(LLVM.LLVMX86FastcallCallConv),
    ARMAPCSCall(LLVM.LLVMARMAPCSCallConv),
    ARMAAPCSCall(LLVM.LLVMARMAAPCSCallConv),
    ARMAAPCSVFPCall(LLVM.LLVMARMAAPCSVFPCallConv),
    MSP430INTRCall(LLVM.LLVMMSP430INTRCallConv),
    X86ThisCallCall(LLVM.LLVMX86ThisCallCallConv),
    PTXKernelCall(LLVM.LLVMPTXKernelCallConv),
    PTXDeviceCall(LLVM.LLVMPTXDeviceCallConv),
    SPIRFUNCCall(LLVM.LLVMSPIRFUNCCallConv),
    SPIRKERNELCall(LLVM.LLVMSPIRKERNELCallConv),
    IntelOCLBICall(LLVM.LLVMIntelOCLBICallConv),
    X8664SysVCall(LLVM.LLVMX8664SysVCallConv),
    Win64Call(LLVM.LLVMWin64CallConv),
    X86VectorCallCall(LLVM.LLVMX86VectorCallCallConv),
    HHVMCall(LLVM.LLVMHHVMCallConv),
    HHVMCCall(LLVM.LLVMHHVMCCallConv),
    X86INTRCall(LLVM.LLVMX86INTRCallConv),
    AVRINTRCall(LLVM.LLVMAVRINTRCallConv),
    AVRSIGNALCall(LLVM.LLVMAVRSIGNALCallConv),
    AVRBUILTINCall(LLVM.LLVMAVRBUILTINCallConv),
    AMDGPUVSCall(LLVM.LLVMAMDGPUVSCallConv),
    AMDGPUGSCall(LLVM.LLVMAMDGPUGSCallConv),
    AMDGPUPSCall(LLVM.LLVMAMDGPUPSCallConv),
    AMDGPUCSCall(LLVM.LLVMAMDGPUCSCallConv),
    AMDGPUKERNELCall(LLVM.LLVMAMDGPUKERNELCallConv),
    X86RegCallCall(LLVM.LLVMX86RegCallCallConv),
    AMDGPUHSCall(LLVM.LLVMAMDGPUHSCallConv),
    MSP430BUILTINCall(LLVM.LLVMMSP430BUILTINCallConv),
    AMDGPULSCall(LLVM.LLVMAMDGPULSCallConv),
    AMDGPUESCall(LLVM.LLVMAMDGPUESCallConv)
}

public enum class Visibility(public override val value: Int) :
    OrderedEnum<Int> {
    Default(LLVM.LLVMDefaultVisibility),
    Hidden(LLVM.LLVMHiddenVisibility),
    Protected(LLVM.LLVMProtectedVisibility)
}

public enum class LandingPadClauseType(public override val value: Int) :
    OrderedEnum<Int> {
    Catch(LLVM.LLVMLandingPadCatch),
    Filter(LLVM.LLVMLandingPadFilter)
}

public enum class AtomicOrdering(public override val value: Int) :
    OrderedEnum<Int> {
    NotAtomic(LLVM.LLVMAtomicOrderingNotAtomic),
    Unordered(LLVM.LLVMAtomicOrderingUnordered),
    Monotonic(LLVM.LLVMAtomicOrderingMonotonic),
    Acquire(LLVM.LLVMAtomicOrderingAcquire),
    Release(LLVM.LLVMAtomicOrderingRelease),
    AcquireRelease(LLVM.LLVMAtomicOrderingAcquireRelease),
    SequentiallyConsistent(LLVM.LLVMAtomicOrderingSequentiallyConsistent)
}

public enum class AtomicRMWBinaryOperation(public override val value: Int) :
    OrderedEnum<Int> {
    Xchg(LLVM.LLVMAtomicRMWBinOpXchg),
    Add(LLVM.LLVMAtomicRMWBinOpAdd),
    Sub(LLVM.LLVMAtomicRMWBinOpSub),
    And(LLVM.LLVMAtomicRMWBinOpAnd),
    Nand(LLVM.LLVMAtomicRMWBinOpNand),
    Or(LLVM.LLVMAtomicRMWBinOpOr),
    Xor(LLVM.LLVMAtomicRMWBinOpXor),
    Max(LLVM.LLVMAtomicRMWBinOpMax),
    Min(LLVM.LLVMAtomicRMWBinOpMin),
    UMax(LLVM.LLVMAtomicRMWBinOpUMax),
    UMin(LLVM.LLVMAtomicRMWBinOpUMin),
    // LLVM 10.0.0 Doxygen documents these but they are not present for LLVM 9
    //
    // LLVMAtomicRMWBinOpFAdd(LLVM.LLVMAtomicRMWBinOpFAdd),
    // LLVMAtomicRMWBinOpFSub(LLVM.LLVMAtomicRMWBinOpFSub)
}

public enum class AttributeIndex(public override val value: Long) :
    OrderedEnum<Long> {
    Return(LLVM.LLVMAttributeReturnIndex),
    Function(LLVM.LLVMAttributeFunctionIndex)
}

public enum class UnnamedAddress(public override val value: Int) :
    OrderedEnum<Int> {
    None(LLVM.LLVMNoUnnamedAddr),
    Local(LLVM.LLVMLocalUnnamedAddr),
    Global(LLVM.LLVMGlobalUnnamedAddr)
}

public enum class ModuleFlagBehavior(public override val value: Int) :
    OrderedEnum<Int> {
    Error(LLVM.LLVMModuleFlagBehaviorError),
    Warning(LLVM.LLVMModuleFlagBehaviorWarning),
    Require(LLVM.LLVMModuleFlagBehaviorRequire),
    Override(LLVM.LLVMModuleFlagBehaviorOverride),
    Append(LLVM.LLVMModuleFlagBehaviorAppend),
    AppendUnique(LLVM.LLVMModuleFlagBehaviorAppendUnique)
}

public enum class DiagnosticSeverity(public override val value: Int) :
    OrderedEnum<Int> {
    Error(LLVM.LLVMDSError),
    Warning(LLVM.LLVMDSWarning),
    Remark(LLVM.LLVMDSRemark),
    Note(LLVM.LLVMDSNote)
}

public enum class DLLStorageClass(public override val value: Int) :
    OrderedEnum<Int> {
    Default(LLVM.LLVMDefaultStorageClass),
    DLLImport(LLVM.LLVMDLLImportStorageClass),
    DLLExport(LLVM.LLVMDLLExportStorageClass)
}

public enum class InlineAsmDialect(public override val value: Int) :
    OrderedEnum<Int> {
    ATT(LLVM.LLVMInlineAsmDialectATT),
    Intel(LLVM.LLVMInlineAsmDialectIntel)
}