public enum Dir {
    L, U, R, D, STOP
}
//int dir = 1,2,3,4  dir = 5时在程序中会出错，而使用枚举，编译的时候就会直接报错，能尽早暴露错误最好
//Enum 赋错值了在编译期间就可以报错，