package com.example.adfmp_settings

interface WordVerifier {
    enum class VerifierVerdict {
        INTERNAL_ERROR {
            override fun errorMessage(): String? {
                return "Слово не принято. Проверьте интернет-соединение"
            }
        },
        OK {
            override fun errorMessage(): String? {
                return null
            }
        },
        ALREADY_USED {
            override fun errorMessage(): String? {
                return "Слово уже использовано"
            }
        },
        LETTER_MISMATCH {
            override fun errorMessage(): String? {
                return "Первая буква слова не совпадает с последней буквой предыдущего"
            }
        },
        UNKNOWN_WORD {
            override fun errorMessage(): String? {
                return "Слово не найдено в словаре"
            }
        };
        abstract fun errorMessage(): String?
    }

    fun verify(word: String): VerifierVerdict
}