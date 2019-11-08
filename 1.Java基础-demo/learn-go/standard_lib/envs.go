package standard_lib

import (
    "fmt"
    "os"
    "strings"
)

func EnvsExp() {
    _ = os.Setenv("Foo", "1")
    fmt.Println("Foo: ", os.Getenv("Foo"))
    fmt.Println("Bar: ", os.Getenv("Bar"))
    fmt.Println()

    for _, e := range os.Environ() {
        pair := strings.Split(e, "=")
        fmt.Println(pair[0])
        fmt.Println()

        fmt.Println(e)
    }
}
