package standard_lib

import (
    "bytes"
    "fmt"
    "regexp"
)

func RegexExp() {
    // match, _ := regexp.MatchString("^p([a-z]+)ch$", "peach")
    // fmt.Println(match)

    r, _ := regexp.Compile("p([a-z]+)ch")
    fmt.Println(r.MatchString("peach"))
    fmt.Println(r.FindString("peach punch"))
    fmt.Println(r.FindStringIndex("peach punch"))
    fmt.Println()

    fmt.Println(r.FindStringSubmatch("peach punch"))
    fmt.Println(r.FindStringSubmatchIndex("peach punch"))
    fmt.Println()

    fmt.Println(r.FindAllString("peach punch pinch", -1))
    fmt.Println(r.FindAllString("peach punch pinch", 2))
    fmt.Println()

    fmt.Println(r.FindAllStringSubmatch("peach punch pinch", -1))
    fmt.Println(r.FindAllStringSubmatchIndex("peach punch pinch", -1))
    fmt.Println()

    str := "peach"
    // type cast
    s := []byte(str)
    fmt.Println(r.Match(s))
    fmt.Println()

    r = regexp.MustCompile("p([a-z]+)ch")
    fmt.Println(r)
    fmt.Println()

    fmt.Println(r.ReplaceAllString("a peach", "<fruit>"))

    in := []byte("a peach")
    out := string(r.ReplaceAllFunc(in, bytes.ToUpper))
    fmt.Println(out)
}
