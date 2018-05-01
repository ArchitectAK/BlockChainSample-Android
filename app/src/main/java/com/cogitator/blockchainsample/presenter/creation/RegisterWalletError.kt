package com.cogitator.blockchainsample.presenter.creation


/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 29/04/2018 (MM/DD/YYYY)
 */


abstract class RegisterWalletError {
    enum class Email {
        NONE, EMPTY, NOT_VALID
    }

    enum class Password {
        NONE, EMPTY, NOT_VALID
    }

    enum class PasswordConfirm {
        NONE, EMPTY, NOT_EQUAL
    }

}