package control_flow

import (
    "errors"
    "fmt"
    "log"
    "os"
)

// throw error
func f1(alan int) (int, error) {
    if alan == 42 {
        return -1, errors.New("can`t work with 42")
    }
    return alan + 3, nil
}

// <editor-fold desc="@ customize error type">
type ArgError struct {
    arg  int
    prob string
}

func (*ArgError) Error() string {
    panic("implement me")
}

func f2(arg int) (int, error) {
    if arg == 42 {
        return -1, &ArgError{arg, "can`t work with 42"}
    }
    return arg + 3, nil
}

// </editor-fold>

func PanicExp() {
    if _, err := os.Create("/tmp/file"); err != nil {
        panic(err)
    }
}

// <editor-fold desc="@ Defer exp">
func DeferExp() {
    f := creatFile("/tmp/defer.text")
    defer closeFile(f)
    writeFile(f)
}

func creatFile(p string) *os.File {
    fmt.Println("creating")
    f, err := os.Create(p);
    if err != nil {
        fmt.Println(err)
    }
    return f
}

func writeFile(file *os.File) {
    fmt.Println("writing")
    _, _ = fmt.Fprintln(file, "data")
}

func closeFile(file *os.File) {
    fmt.Println("closing")
    _ = file.Close()
}

// </editor-fold>

// <editor-fold desc="@ Two error handle pattern">
// an error decorator
func errorHandler(fn func(a ...interface{})) (func(a ...interface{})) {
    return func(a ...interface{}) {
        defer func() {
            if err := recover().(error); err != nil {
                log.Printf("[%v] caught panic: %v", "whatFunc", err)
            }
        }()
        fn(a)
    }
}

func check(err error) {
    if err != nil {
        panic(err)
    }
}

// </editor-fold>
