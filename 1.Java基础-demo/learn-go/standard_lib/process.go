package standard_lib

import (
    "fmt"
    "os"
)

func SubProcess() {
    env := os.Environ()
    proAttr := &os.ProcAttr{
        Env: env,
        Files: []*os.File{
            os.Stdin,
            os.Stdout,
            os.Stderr,
        },
    }

    pid, err := os.StartProcess("/bin/ls", []string{"ls", "-l", "/home/alanding/0_Dev"}, proAttr)
    if err != nil {
        fmt.Printf("Error %v starting process\n", err)
        os.Exit(1)
    }
    fmt.Printf("The process id is %v\n", pid)
}
