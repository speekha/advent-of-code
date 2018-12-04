package com.adventofcode.dec2020.day04

class PassportScanner {

    fun scanCompletePassports(input: List<String>): Int = parsePassports(input).count { it.hasMandatoryFields() }

    fun scanValidPassports(input: List<String>): Int = parsePassports(input).count { it.isValid() }

    private fun parsePassports(input: List<String>): List<Passport> {
        val groupData = input.map { it.takeIf { it.isNotBlank() } ?: "\n" }.joinToString(" ").split("\n")
        return groupData.map { Passport(it) }
    }

    data class Passport(val fields: Map<String, String>) {

        constructor(data: String) : this(parseFields(data))

        val mandatoryFields = listOf("byr",
                "iyr",
                "eyr",
                "hgt",
                "hcl",
                "ecl",
                "pid")

        fun hasMandatoryFields() = mandatoryFields.all { fields.containsKey(it) }

        fun isValid() = try {
            validateBirth()
                    && validateIssueDate()
                    && validateExpiryDate()
                    && validateHeight()
                    && validateHairColor()
                    && validateEyeColor()
                    && validatePassportId()
        } catch (e: Throwable) {
            false
        }

        private val idValidator = Regex("\\d{9}")
        private fun validatePassportId(): Boolean = fields["pid"]?.let {
            idValidator.matches(it)
        } ?: false

        private fun validateEyeColor(): Boolean = fields["ecl"] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        private val colorValidator = Regex("#[0123456789abcdef]{6}")
        private fun validateHairColor(): Boolean = fields["hcl"]?.let { colorValidator.matches(it) } ?: false

        private fun validateHeight(): Boolean = try {
            val height = fields["hgt"] ?: error("Missing height")
            when {
                height.endsWith("cm") -> height.dropLast(2).toInt() in 150..193
                height.endsWith("in") -> height.dropLast(2).toInt() in 59..76
                else -> false
            }
        } catch (e: Throwable) {
            false
        }

        private fun validateExpiryDate() = fields["eyr"]?.toInt() in 2020..2030

        private fun validateIssueDate() = fields["iyr"]?.toInt() in 2010..2020

        private fun validateBirth() = fields["byr"]?.toInt() in 1920..2002

        companion object {

            fun parseFields(data: String) = data.split(" ")
                    .filter { it.isNotBlank() }
                    .map { it.split(":") }
                    .associate { it[0] to it[1] }
        }
    }
}
