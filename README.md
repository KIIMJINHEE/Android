### 30개 프로젝트로 배우는 Android 앱 개발 with Kotlin 초격차 패키지 Online. 

###### https://github.com/orgs/Fastcampus-Android-Lecture-Project-2021/repositories?q=&type=all&language=&sort=name









package com.example.memo

/*
fun main(){

    //3. String Templete
    val name = "Joyce"
    val lastName = "Hong"
    // println("My name is ${name + " " + lastName}")

    //주석처리
    /*
    *
    *
    * */

    //4.조건식
    fun maxBy(a: Int, b: Int): Int{
        if(a > b) return a
        else return b
    }
    //위 함수와 내용 동일, 더 간결
    fun maxBy2(a: Int, b: Int): Int = if(a > b) a else b
}
*/

//1. 함수
fun helloworld() : Unit{
    println("Hello, World!")
}

fun add(a:Int, b:Int): Int{
    return a + b
}

//2. val과 var
//val = value
//var = variable
fun hi(){
    val a : Int = 10
    var b : Int = 9

    var e : String

    //자동추론이기 때문에 자료형 생략 가능
    val c = 100
    var d = 100
    var name = "Joyce"
}

fun checkNum(score: Int) {
    when (score) {
        0 -> println("This is 0")
        1 -> println("This is 1")
        2, 3 -> println("This is 2 or 3")
        else -> println("I don't know")
    }

    //expression으로(연산의 결과가 존재하면) 사용되면, 반드시 when에는 else 적어야 한다
    var b = when (score) {
        1 -> 1
        2 -> 2
        else -> 10
    }

    println(b)

    when (score) {
        in 99..100 -> println("you are genius")
        in 10..88 -> println("not bad")
        else -> println("too bad")
    }
}

//[Expression(등호(=)존재) vs Statement 차이]

//*코를린에서 모든 함수는 Expression
//ㄴ 아무리 return 값이 없더라고 unit 반환하므로
//*자바에서 void 함수는 statement

//*자바에서 모든 if문은 statement
//*코틀린에서 if문, when문은 statement, Expression 둘 다 사용 가능

//5. array와 list
//Array : 메모리가 할당되어 있음
//List : 1.List(수정불가), 2.MutableList(수정가능, 대표 : arrayList)

fun array(){
    val array = arrayOf(1, 2, 3)
    val list = listOf(1, 2, 3)

    val array2 = arrayOf(1, "d", 3.14f)
    val list2 = listOf(1, "d", 11L)

    array[0] = 3
    //list[0] = 1 불가
    var result = list.get(0)

    val arrayList = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)

    //arrayList = arrayListOf() 참조값이 변하므로 불가
}

//6. 반복문 : for / while
fun forandwhile(){
    val students : ArrayList<String> = arrayListOf("joyce", "james", "jenny", "jennifer")

    for(name in students){
        println("${name}")
    }

    var sum : Int = 0
    //for(i in 1..10){
    //for(i in 1..10 step 2){
    //for(i in 10 downTo 1){
    for(i in 1 until 100){ // 1~99까지
        sum += i
    }
    println(sum)

    var index = 0
    while(index < 10){
        //println("current index : ${index}")
        index++
    }

    for((index, name) in students.withIndex()){
        println("index : ${index + 1}" + ", name : " + "${name}")
    }
}

//7. NonNull 과 Nullable
fun nullchekc(){
    //NPE : Null pointer Exception
    //컴파일 타임 : "소스 코드를 실행 파일로 변환"하는 프로그래밍 수명주기 단계
    //런 타임 : 컴파일 시간에 생성 된 "실행 파일 실행"하는 프로그래밍 수명주기 단계
    //NPE는 런타임에서만 잡히기 때문에 불편
    //ㄴ 코틀린에서는 이 불편함을 잡기 위해 나온게 '?'

    var name : String = "joyce"
    var nullName : String? = null //?붙이면 Nullable 타입이 된다
    var nameInUpperCase = name.toUpperCase()

    var nullNameInUpperCase = nullName?.toUpperCase() //null이면 null반환,
                                                      //null아니면 ?.뒤 출력
    //?: (엘비스 연산자)
    val lastName : String? = null
    val fullName = name + " " + (lastName?: "NO lastName")//null이면, ?:뒤 출력
                                                          //null아니면 ?:앞 출력
    println(fullName)


}

//!! : Nullable 타입으로 지정되어 있는 변수이지만, NUll 아니라고 확신해줄 떄 사용
fun ignoreNulls(str : String?){
    //val mNotNull : String = str!! //"str이 null일리 절대 없어"
    //val upper = mNotNull.toUpperCase() //?.써주지 않아도 됨(위의 !! 때문)

    //val email : String? = "skesswswkk@naver.com"
    val email : String? = null
    email?.let{//email이 null이 아니면 뒤 .let{} 실행
        println("my email is ${email}")
    }
}

//tmp?. -> null이 아니면 뒤 실행
//tmp?: -> null이면 뒤 실행

//8. class
//class Human constructor(val name : String){
//open 적어주는 이유 : 코틀린은 기본적으로 Final class이기 때문에
// 아래 class Korean : Human(){ 에서 상속받기 위해
open class Human (val name : String = "Anonymous"){ //constructor 생략 가능
                    //ㄴproperty(val name)과 생성자를 같이 쓴 꼴
            //ㄴ생성자 위치에서 코드블럭 실행 불가
            //ㄴsol : init()함수 사용

    //주생성자
    init{
        println("New human has been born!!")
    }

    //부생성자 -> 주생성자의 위임을 받아야 한다(this(name))
    constructor(name : String, age : Int) : this(name){
        println("My name is ${name}, ${age} years old.")
    }

    //property정의
    //val name = "joyce"
    //ㄴHuman()객체를 생성할 때 name을 정의하고 싶으면, 생성자(Constructor) 정의하면 됨

    //함수 정의
    fun eatingCake(){
        println("This is so yummy!!")
    }

    open fun singASong(){ //class Korean 에서 override 해주기 위함
        println("lalala")
    }
}

//class 상속
class Korean : Human(){
    override fun singASong(){
        super.singASong()
        println("랄랄라")

        //property 도 override 가능
        println("My name is ${name }")
    }
}

fun main(){
    /*
    //1. (1, 2, 3 - 주생성자 먼저 실행된다)
    val human = Human("minsu") //자바와 달리(new Class명), class명만 적어도 됨

    //2.
    val stranger = Human()
    //4.
    human.eatingCake()

    //3.
    val constructor_human = Human("minhee", 23)

    //5.
    println("${human.name}")
    //6.
    println("${stranger.name}")
    //7.
    println("${constructor_human.name}")
     */
    val korean = Korean()
    korean.singASong()
}
