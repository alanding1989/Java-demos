package standard_lib

import (
    "fmt"
    "os"
)

func CmdLineArgs() {
    argsWithProg := os.Args
    argsWithoutProg := os.Args[1:]

    arg := os.Args[0]

    fmt.Println(argsWithProg)
    fmt.Println(argsWithoutProg)
    fmt.Println(arg)
}
