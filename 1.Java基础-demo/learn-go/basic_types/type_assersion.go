package basic_types

import (
    "fmt"
    "reflect"
)

type Stringer interface {
    String() string
}

type alan struct {
}

func (self *alan) String() string {
    defer fmt.Println()
    return "alan"
}

func CheckImplInterface() {
    // v := new(alan)
    // if sv, ok := v.(Stringer); ok {
    //     fmt.Printf("v implements String(): %s\n", sv.String())
    // }
}

func ReflectExp() {
    var x float64 = 4.3
    fmt.Println("type: ", reflect.TypeOf(x))

    v := reflect.ValueOf(x)
    fmt.Println("value: ", v)
    fmt.Println("type: ", v.Type())
    fmt.Println("kind: ", v.Kind())
    fmt.Println("value: ", v.Float())
    fmt.Println()

    fmt.Println(v.Interface())
    fmt.Printf("value is %5.2e\n", v.Interface())
    fmt.Println()

    y := v.Interface().(float64)
    fmt.Println(y)
}
