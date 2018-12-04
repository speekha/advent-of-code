package com.adventofcode.dec2016.day2

class CodeBreaker {


    fun parseCode(input: String, nextDigit: (Char, Char) -> Char): String {
        var digit: Char? = null
        return input.split("\n").map { row ->
            digit = parseDigit(row, digit, nextDigit)
            digit
        }.joinToString("")
    }

    fun parseDigit(path: String, start: Char? = '5', nextDigit: (Char, Char) -> Char): Char {
        var digit = start ?: '5'
        for (c in path) {
            digit = nextDigit(digit, c)
        }
        return digit
    }

    fun simpleKeypad(digit: Char, c: Char) = when (c) {
        'U' -> when (digit) {
            '4' -> '1'
            '5' -> '2'
            '6' -> '3'
            '7' -> '4'
            '8' -> '5'
            '9' -> '6'
            else -> digit
        }
        'D' -> when (digit) {
            '1' -> '4'
            '2' -> '5'
            '3' -> '6'
            '4' -> '7'
            '5' -> '8'
            '6' -> '9'
            else -> digit
        }
        'L' -> when (digit) {
            '2' -> '1'
            '3' -> '2'
            '5' -> '4'
            '6' -> '5'
            '8' -> '7'
            '9' -> '8'
            else -> digit
        }
        'R' -> when (digit) {
            '1' -> '2'
            '2' -> '3'
            '4' -> '5'
            '5' -> '6'
            '7' -> '8'
            '8' -> '9'
            else -> digit
        }
        else -> digit
    }

    fun complexKeypad(digit: Char, c: Char) = when (c) {
        'U' -> when (digit) {
            '6' -> '2'
            'A' -> '6'
            '3' -> '1'
            '7' -> '3'
            'B' -> '7'
            'D' -> 'B'
            '8' -> '4'
            'C' -> '8'
            else -> digit
        }
        'D' -> when (digit) {
            '2' -> '6'
            '6' -> 'A'
            '1' -> '3'
            '3' -> '7'
            '7' -> 'B'
            'B' -> 'D'
            '4' -> '8'
            '8' -> 'C'
            else -> digit
        }
        'L' -> when (digit) {
            '4' -> '3'
            '3' -> '2'
            '9' -> '8'
            '8' -> '7'
            '7' -> '6'
            '6' -> '5'
            'C' -> 'B'
            'B' -> 'A'
            else -> digit
        }
        'R' -> when (digit) {
            '2' -> '3'
            '3' -> '4'
            '5' -> '6'
            '6' -> '7'
            '7' -> '8'
            '8' -> '9'
            'A' -> 'B'
            'B' -> 'C'
            else -> digit
        }
        else -> digit
    }

}


fun main() {
    val input = "LLULLLRLDLLLRLUURDDLRDLDURULRLUULUDDUDDLLLURRLDRRLDRRRLDUDLRDLRRDLLDUDUDUDRLUDUUDLLLRDURUDUULUDLRDUUUDUUDURLDUULLRDLULDUURUDRDDLDRLURLRURRDUURLRLUURURUUULLRLLULRUURLULURDLLRRUDLUDULDRDRLRULUURRDRULLRUUUDLRLDLUURRRURDLUDDRRUDRLUDRDLLLLLRULLDUDRLRRDDULDLRUURRRRRLDLDLRDURDRUUURDLRDDDDULURRRRDUURLULLLDLRULRDULRUDLRRLRDLLRLLLUDDLRDRURDDLLLLDUDRDLRURRDLRDDDLDULDRLRULUUDRRRUUULLLURRDDUULURULDURRLLULLDRURUUULRLRDRRUDRDRRDURRUUUULDRDDDUDLDDURLLRR\n" +
            "LDLRRRUURDLDDRLRRDLLULRULLLUDUUDUDLRULLDRUDRULLDULURDRDDLRURDDULLLLDLRDRDRDDURLURLURLUDRDDRDULULUDDRURRDLLDUURDRDDLRLLURRDLRDDULDLULURDRDLUDRRUUDULLULURRDUDRUUUDRULDLDURLRRUDURLDLRRUURRRURDLUDRLDUDRRUDUURURUDDUUDRDULRDLUDRRRLDRURLLRDDDLUDRDUDURDDDRRDDRRRLLRRDDLDDLRUURRURDLLDRLRRDLLUDRRRURURLRDRLLRLRLRULLRURLDLRRULLRRRDULUUULDRDLLURDDLDLRDRLUUDLLUDDLDRRLDLRUDRUDLLUURLLULURUDUDRLULLUDRURDDLDLDDUDLRDDRRURLRLLUDDUDRUURRURRULDRLDDRLLRRLDDURRDLDULLLURULLLRUURLRRRRUUULRLLLURRLRLRUDRDUUUDUUUDDLULLDLLLLDLDRULDRUUULDDDLURLDLRLULRUDDDDURDDLU\n" +
            "RURLURRDLDULLULDDDLRUULLUURLRUDRUDRRUDDLDDDDRRDLRURLRURLDDDUDDUURRDRULDRRRULRDRDDLRUDULRLURDUUDRRLDLRDRURDLDRRRRDRURUUDDDLLRDRDUDUDUDLLULURULRRLRURUULUULDDDDURULRULLRUDUURLURDUDLUDLUDRLLDUUDUULRLRLUUDRDULDULRURDRRRULRUDLRURDDULUDULLRLRURURUULLULDRURLLRRUUDDUUURRDLURUURULRDRRDDUDULRDDLUDLURURUURDRULLRDDLLRDDLDRDUDRRDLUURRLRLUURRULUDURLDDRLLURRDDDLDDRURULLDDRLUDDLRLURDUDULLRDULLLDLLUDDRUDRUDDUUDRDRULRL\n" +
            "RLRDRDULULUDLUDRDRLUDLDLLUDURULDDDUDLRURLLRLRLDLDRLDURDLRRURLULLULURLLDRRDRLUDRLRDLLULRULURRURURUULRDUDLLRDLRRRRRLUURDRRRDLRUDLLDLLDLRUUUDLLLDDDLRDULLRUUDDRLDDURRRDLRLRLDDDDLRDRULLUURUUDRRLLRLLRDDLLRURRRRDRULRRLLRLLLRLDRRLDDDURRURLDURUURRLRLRLDRURULLRLRUDLDUURDLLRLDLURUUUDLLRDRDDDDDDRLDRRRLRRRRURUDLDDRDLLURUDLRRLDDDLUDUDUULRDULULUDDULUUDLLLLRLDDUUULRLRDULURDURRRURRULURRRDRDLDDURDLURUDURRRDDRLRLUDLUDDLUULLDURLURDDUDDLRUUUDRLLDRURL\n" +
            "ULUDLLUDDULRUURDRURDUDUDLUURDDDRRLUDURURDRURRLDRDURLRLLRRDDRRDRRRUULURUDURUDULRRRRDDLDURRLRRDUDDDRLLLULDRLRLURRDUURDURRRURRDLUDUDDRLDLURRRDDRLLRDRDDRDURRRRLURRLUDDURRULRUDUDULDRUDDRULLUUULDURRRLDRULLURULLRUDLDUDDLDULDLUUDRULULDLLDRULLRUULDUDUUDRLRRLDLUULUDLLDDRLRRDDLLURURDULRRDDRURDRLRLULDLDURULLUUUDURURDLDUDDDDUUULUDLUURRULLDLRLURDLURLRLDDURRLDDRRRDUUULLUULDLLDLLDDRLRRUDLULDRLULDULULRRLRULUUURURUUURDUUDDURLLUDDRLRDDLUURRUULRDLDDRLULUULRDRURLUURDRDUURUDLRR"
    with(CodeBreaker()) {
        println("Code #1: ${parseCode(input, this::simpleKeypad)}")
        println("Code #2: ${parseCode(input, this::complexKeypad)}")
    }
}