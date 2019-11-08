package standard_lib

import (
    "bufio"
    "fmt"
    "io"
    "io/ioutil"
    "os"
    "strings"
)

func check(err error) {
    if err != nil {
        panic(err)
    }
}

func IoReadExp() {
    fname := "/home/alanding/0_Dev/sparktest.txt"

    // read whole file
    dat, err := ioutil.ReadFile(fname)
    check(err)
    fmt.Println(string(dat))

    // simple readonly file
    f, err := os.Open(fname)
    check(err)
    defer f.Close()

    // read bytes from *File
    b1 := make([]byte, 10)
    n1, err := f.Read(b1)
    check(err)
    fmt.Printf("%d bytes @ %s\n", n1, string(b1))
    fmt.Println()

    // read byte from a specific location
    o2, err := f.Seek(11, 0)
    check(err)
    b2 := make([]byte, 2)
    n2, err := f.Read(b2)
    fmt.Printf("%d bytes @ %d: %s\n", n2, o2, string(b2))
    fmt.Println()

    o3, err := f.Seek(11, 0)
    check(err)
    b3 := make([]byte, 2)
    n3, err := io.ReadAtLeast(f, b3, 2)
    check(err)
    fmt.Printf("%d bytes @ %d: %s\n", n3, o3, string(b3))

    // back to the start again
    _, err = f.Seek(0, 0)
    check(err)

    // specify file mode, and use cache buffer
    file, err := os.OpenFile(fname, os.O_RDONLY|os.O_CREATE, 0666)
    check(err)
    defer file.Close()

    r4 := bufio.NewReader(file)
    b4, err := r4.Peek(5)
    fmt.Printf("5 bytes: %s\n", string(b4))
}

func IoWriteExp() {
    fname := "/home/alanding/0_Dev/rustfileio.txt"
    // directly write to file
    d1 := []byte("hello\ngo\n")
    err := ioutil.WriteFile(fname, d1, 0644)
    check(err)

    //
    f, err := os.Create("/tmp/data")
    check(err)
    defer f.Close()

    // write bytes
    d2 := []byte{115, 111, 109, 101, 10}
    n2, err := f.Write(d2)
    check(err)
    fmt.Printf("wrote %d bytes\n", n2)

    // write strings
    n3, err := f.WriteString("writes\n")
    check(err)
    fmt.Printf("wrote %d bytes\n", n3)
    // flush
    _ = f.Sync()
    fmt.Println()

    // set file mode
    f, err = os.OpenFile(fname, os.O_APPEND|os.O_WRONLY, 0644)
    check(err)
    defer f.Close()
    // use buffer, cache
    wbuf := bufio.NewWriter(f)
    n4, err := wbuf.WriteString("buffered\n")
    fmt.Printf("wrote %d bytes\n", n4)
    _ = wbuf.Flush()
}

func LineFilter() {
    scanner := bufio.NewScanner(os.Stdin)

    for scanner.Scan() {
        ucl := strings.ToUpper(scanner.Text())
        fmt.Println(ucl)
        fmt.Printf("%\n", )
    }

    if err := scanner.Err(); err != nil {
        _, _ = fmt.Fprintln(os.Stderr, "error:", err)
        os.Exit(1)
    }
}
