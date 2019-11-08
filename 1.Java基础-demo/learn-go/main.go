package main

import (
    "learn-go/basic_types"
    goroutine "learn-go/concurrent"
    "learn-go/control_flow"
)

func main() {
    basic_type()
    // control_structure()
    // compound_type()
    // control_structure()
    // concurrent()
    // std()
    // algorithm()
}

func basic_type() {
    // basic_types.Const()
    // basic_types.Variable()
    // basic_types.CheckImplInterface()
    basic_types.ReflectExp()
}

func control_structure() {
    // control_flow.Switch()

    // loop := control_flow.NewLoopObj()
    // loop.Iter()
    // loop.Loop()

    // @ functions
    // control_flow.Vals()
    // control_flow.Sum(1,2,3)
    // @ use slice as var, pay attention to the grammar
    // control_flow.Sum([]int{1, 2, 3, 4}...)

    // closure impl generator
    // var nextInt func() int = control_flow.IntSeq()
    // fmt.Println(nextInt())
    // fmt.Println(nextInt())
    // control_flow.PanicExp()
    control_flow.DeferExp()
}

func compound_type() {
    basic_types.Arrays()
    basic_types.Slice()
    basic_types.Dict()
}

func concurrent() {
    // goroutine.Goroutine()
    // goroutine.Channel()
    // goroutine.SyncChannel()
    // goroutine.DirectionOfChannel()
    // goroutine.SelectBlockChan()
    // goroutine.TimeoutChan()
    // goroutine.NonBlockChan()
    // goroutine.CloseChan()
    // goroutine.IterChan()
    // goroutine.Rate_limiting()

    // classic concurrent
    // goroutine.AtomicExp()
    goroutine.MutexSync()
    goroutine.ChanSync()
}

func std() {
    // standard_lib.TimerExp()
    // standard_lib.TickerExp()
    // standard_lib.WorkerPool()
    // standard_lib.StrFmt()
    // standard_lib.RegexExp()
    // standard_lib.JsonExp()
    // standard_lib.TimeExp()
    // standard_lib.TimeStamp()
    // standard_lib.IoReadExp()
    // standard_lib.IoWriteExp()
    // standard_lib.CmdLineArgs()
    // standard_lib.EnvsExp()
}

func algorithm() {
    // algo.PrimitiveSort()
    // algo.CustomizeSort()
    // algo.StringExp()
}
