package ericdiaz.program.currencyconveterlive2019.view.dialpad

sealed class Dial(val dialSymbol: String) {

    object One : Dial("1")
    object Two : Dial("2")
    object Three : Dial("3")
    object Four : Dial("4")
    object Five : Dial("5")
    object Six : Dial("6")
    object Seven : Dial("7")
    object Eight : Dial("8")
    object Nine : Dial("9")
    object Dot : Dial(".")
    object Zero : Dial("0")
    object Delete : Dial("Delete_Symbol")

}