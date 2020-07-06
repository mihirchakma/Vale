#ifndef EXPRESSIONS_H_
#define EXPRESSIONS_H_

#include <llvm-c/Core.h>
#include <functional>
#include <unordered_map>

#include "metal/ast.h"
#include "metal/instructions.h"
#include "globalstate.h"
#include "function/function.h"
#include "function/expression.h"
#include "function/expressions/shared/shared.h"

LLVMValueRef translateDestructure(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    Destroy* destructureM);

LLVMValueRef translateConstruct(
    AreaAndFileAndLine from,
    GlobalState* globalState,
    LLVMBuilderRef builder,
    Reference* desiredReference,
    const std::vector<LLVMValueRef>& membersLE);

LLVMValueRef translateCall(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    Call* call);

LLVMValueRef translateInterfaceCall(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    InterfaceCall* call);

LLVMValueRef translateExternCall(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    ExternCall* expr);

LLVMValueRef translateIf(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    If* iff);

LLVMValueRef translateWhile(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    While* whiile);

LLVMValueRef translateDiscard(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    Discard* discardM);

LLVMValueRef translateNewArrayFromValues(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    NewArrayFromValues* newArrayFromValues);

LLVMValueRef translateConstructUnknownSizeArray(
    GlobalState* globalState,
    FunctionState* functionState,
    LLVMBuilderRef builder,
    ConstructUnknownSizeArray* constructUnknownSizeArray);

LLVMValueRef translateConstantStr(
    AreaAndFileAndLine from,
    GlobalState* globalState,
    LLVMBuilderRef builder,
    ConstantStr* constantStr);

#endif