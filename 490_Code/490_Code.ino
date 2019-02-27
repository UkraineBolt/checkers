/*
 * pin constants
*/
const int ledPin = 30;/* A4 */                                 /* H5 */ /* 13 */
const int ldrPin = A0;/* For Ardunio with blue tap on it */    /* For Ardunio with NO blue tap on it */
const int ledPin1 = 32;/* A2 */                                /* H7 */ /* 5 */     
const int ldrPin1 = A1;
const int ledPin2 = 2; /* B3 */                                /* G6 */ /* 9 */
const int ldrPin2 = A2;
const int ledPin3 = 3; /* B1 */                                /* G8 */ /* 1 */
const int ldrPin3 = A3;
const int ledPin4 = 4; /* C4 */                                /* F5 */ /* 14*/
const int ldrPin4 = A4;
const int ledPin5 = 5; /* C2 */                                /* F7 */ /* 6*/
const int ldrPin5 = A5;
const int ledPin6 = 6; /* D3 */                                /* E6 */ /* 10 */
const int ldrPin6 = A6;
const int ledPin7 = 7; /* D1 */                                /* E8 */ /* 2 */
const int ldrPin7 = A7;
const int ledPin8 = 8; /* E4 */                                /* D5 */ /* 15 */    
const int ldrPin8 = A8;
const int ledPin9 = 9; /* E2 */                                /* D7 */ /* 7 */
const int ldrPin9 = A9;
const int ledPin10 = 10; /* F3 */                              /* C6 */ /* 11 */
const int ldrPin10 = A10;
const int ledPin11 = 11; /* F1 */                              /* C8 */ /* 3 */
const int ldrPin11 = A11;
const int ledPin12 = 12; /* G4 */                              /* B5 */ /* 16 */
const int ldrPin12 = A12;
const int ledPin13 = 13; /* G2 */                              /* B7 */ /* 8 */
const int ldrPin13 = A13;
const int ledPin14 = 14; /* H3 */                              /* A6 */ /* 4 */
const int ldrPin14 = A14;
const int ledPin15 = 15; /* H1 */                              /* A8 */ /* 12 */
const int ldrPin15 = A15;

/*
 * thresholds
 */
 
int threshA0 = 0;
int threshA1 = 0;
int threshA2 = 0;
int threshA3 = 0;
int threshA4 = 0;
int threshA5 = 0;
int threshA6 = 0;
int threshA7 = 0;
int threshA8 = 0;
int threshA9 = 0;
int threshA10 = 0;
int threshA11 = 0;
int threshA12 = 0;
int threshA13 = 0;
int threshA14 = 0;
int threshA15 = 0;

/*bools for pins*/
bool p0 = false;
bool p1 = false;
bool p2 = false;
bool p3 = false;
bool p4 = false;
bool p5 = false;
bool p6 = false;
bool p7 = false;
bool p8 = false;
bool p9 = false;
bool p10 = false;
bool p11 = false;
bool p12 = false;
bool p13 = false;
bool p14 = false;
bool p15 = false;

void setup() {
Serial.begin(9600);
Serial.setTimeout(50);

pinMode(ledPin, OUTPUT);
pinMode(ldrPin, INPUT);
pinMode(ledPin2, OUTPUT);
pinMode(ldrPin2, INPUT);
pinMode(ledPin3, OUTPUT);
pinMode(ldrPin3, INPUT);
pinMode(ledPin3, OUTPUT);
pinMode(ldrPin4, INPUT);
pinMode(ledPin4, OUTPUT);
pinMode(ldrPin5, INPUT);
pinMode(ledPin5, OUTPUT);
pinMode(ldrPin6, INPUT);
pinMode(ledPin6, OUTPUT);
pinMode(ldrPin7, INPUT);
pinMode(ledPin7, OUTPUT);
pinMode(ldrPin8, INPUT);
pinMode(ledPin8, OUTPUT);
pinMode(ldrPin9, INPUT);
pinMode(ledPin9, OUTPUT);
pinMode(ldrPin10, INPUT);
pinMode(ledPin10, OUTPUT);
pinMode(ldrPin11, INPUT);
pinMode(ledPin11, OUTPUT);
pinMode(ldrPin12, INPUT);
pinMode(ledPin12, OUTPUT);
pinMode(ldrPin13, INPUT);
pinMode(ledPin13, OUTPUT);
pinMode(ldrPin14, INPUT);
pinMode(ledPin14, OUTPUT);
pinMode(ldrPin15, INPUT);
pinMode(ledPin15, OUTPUT);

delay(100);/* starting thresholds */

threshA0 = analogRead(ldrPin);
threshA1 = analogRead(ldrPin1);
threshA2 = analogRead(ldrPin2);
threshA3 = analogRead(ldrPin3);
threshA4 = analogRead(ldrPin4);
threshA5 = analogRead(ldrPin5);
threshA6 = analogRead(ldrPin6);
threshA7 = analogRead(ldrPin7);
threshA8 = analogRead(ldrPin8);
threshA9 = analogRead(ldrPin9);
threshA10 = analogRead(ldrPin10);
threshA11 = analogRead(ldrPin11);
threshA12 = analogRead(ldrPin12);
threshA13 = analogRead(ldrPin13);
threshA14 = analogRead(ldrPin14);
threshA15 = analogRead(ldrPin15);
}

void loop() {
if(Serial.available()>0){
String t = Serial.readString();
readInput(t);
}
  
shootOutput();
/*delay(1000);*/
} 

void readInput(String text) {
  text.trim();
  if(text=="1"){
    if(p0){
      p0=false;
      digitalWrite(ledPin3, LOW);
    }else{
      p0=true;
      digitalWrite(ledPin3, HIGH);
    }
    
  }else if(text=="2"){
    if(p1){
      p1=false;
      digitalWrite(ledPin7, LOW);
    }else{
      p1=true;
      digitalWrite(ledPin7, HIGH);
    }
  }else if(text=="3"){
    if(p2){
      p2=false;
      digitalWrite(ledPin11, LOW);
    }else{
      p2=true;
      digitalWrite(ledPin11, HIGH);
    }
  }else if(text=="4"){
    if(p3){
      p3=false;
      digitalWrite(ledPin14, LOW);
    }else{
      p3=true;
      digitalWrite(ledPin14, HIGH);
    }
  }else if(text=="5"){
    if(p4){
      p4=false;
      digitalWrite(ledPin1, LOW);
    }else{
      p4=true;
      digitalWrite(ledPin1, HIGH);
    }
  }else if(text=="6"){
    if(p5){
      p5=false;
      digitalWrite(ledPin5, LOW);
    }else{
      p5=true;
      digitalWrite(ledPin5, HIGH);
    }
  }else if(text=="7"){
    if(p6){
      p6=false;
      digitalWrite(ledPin9, LOW);
    }else{
      p6=true;
      digitalWrite(ledPin9, HIGH);
    }
  }else if(text=="8"){
    if(p7){
      p7=false;
      digitalWrite(ledPin13, LOW);
    }else{
      p7=true;
      digitalWrite(ledPin13, HIGH);
    }
  }else if(text=="9"){
    if(p8){
      p8=false;
      digitalWrite(ledPin2, LOW);
    }else{
      p8=true;
      digitalWrite(ledPin2, HIGH);
    }
  }else if(text=="10"){
    if(p9){
      p9=false;
      digitalWrite(ldrPin6, LOW);
    }else{
      p9=true;
      digitalWrite(ledPin6, HIGH);
    }
  }else if(text=="11"){
    if(p10){
      p10=false;
      digitalWrite(ledPin10, LOW);
    }else{
      p10=true;
      digitalWrite(ledPin10, HIGH);
    }
  }else if(text=="12"){
    if(p11){
      p11=false;
      digitalWrite(ledPin15, LOW);
    }else{
      p11=true;
      digitalWrite(ledPin15, HIGH);
    }
  }else if(text=="13"){
    if(p12){
      p12=false;
      digitalWrite(ledPin, LOW);
    }else{
      p12=true;
      digitalWrite(ledPin, HIGH);
    }
  }else if(text=="14"){
    if(p13){
      p13=false;
      digitalWrite(ledPin4, LOW);
    }else{
      p13=true;
      digitalWrite(ledPin4, HIGH);
    }
  }else if(text=="15"){
    if(p14){
      p14=false;
      digitalWrite(ledPin8, LOW);
    }else{
      p14=true;
      digitalWrite(ledPin8, HIGH);
    }
  }else if(text=="16"){
    if(p15){
      p14=false;
      digitalWrite(ledPin12, LOW);
    }else{
      p14=true;
      digitalWrite(ledPin12, HIGH);
    }
  }else if(text=="reset"){
    digitalWrite(ledPin, LOW);
    digitalWrite(ledPin1, LOW);
    digitalWrite(ledPin2, LOW);
    digitalWrite(ledPin3, LOW);
    digitalWrite(ledPin4, LOW);
    digitalWrite(ledPin5, LOW);
    digitalWrite(ledPin6, LOW);
    digitalWrite(ledPin7, LOW);
    digitalWrite(ledPin8, LOW);
    digitalWrite(ledPin9, LOW);
    digitalWrite(ledPin10, LOW);
    digitalWrite(ledPin11, LOW);
    digitalWrite(ledPin12, LOW);
    digitalWrite(ledPin13, LOW);
    digitalWrite(ledPin14, LOW);
    digitalWrite(ledPin15, LOW);
    p0=false;
    p1=false;
    p2=false;
    p3=false;
    p4=false;
    p5=false;
    p6=false;
    p7=false;
    p8=false;
    p9=false;
    p10=false;
    p11=false;
    p12=false;
    p13=false;
    p14=false;
    p15=false;
  }
  
}

 void shootOutput(){

  if(threshA0-250>analogRead(ldrPin) || analogRead(ldrPin)>threshA0+250){
    /* fire event */ Serial.println(13);
    delay(1500);
    threshA0=analogRead(ldrPin);
  }else if(threshA1-250>analogRead(ldrPin1) || analogRead(ldrPin1)>threshA1+250){
    /* fire event */ Serial.println(5);
    delay(1500);
    threshA1=analogRead(ldrPin1);
  }else if(threshA2-250>analogRead(ldrPin2) || analogRead(ldrPin2)>threshA2+250){
    /* fire event */ Serial.println(9);
    delay(1500);
    threshA2=analogRead(ldrPin2);
  }else if(threshA3-250>analogRead(ldrPin3) || analogRead(ldrPin3)>threshA3+250){
    /* fire event */ Serial.println(1);
    delay(1500);
    threshA3=analogRead(ldrPin3);
  }else if(threshA4-250>analogRead(ldrPin4) || analogRead(ldrPin4)>threshA4+250){
    /* fire event */ Serial.println(14);
    delay(1500);
    threshA4=analogRead(ldrPin4);
  }else if(threshA5-250>analogRead(ldrPin5) || analogRead(ldrPin5)>threshA5+250){
    /* fire event */ Serial.println(6);
    delay(1500);
    threshA5=analogRead(ldrPin5);
  }else if(threshA6-250>analogRead(ldrPin6) || analogRead(ldrPin6)>threshA6+250){
    /* fire event */ Serial.println(10);
    delay(1500);
    threshA6=analogRead(ldrPin6);
  }else if(threshA7-250>analogRead(ldrPin7) || analogRead(ldrPin7)>threshA7+250){
    /* fire event */ Serial.println(2);
    delay(1500);
    threshA7=analogRead(ldrPin7);
  }else if(threshA8-250>analogRead(ldrPin8) || analogRead(ldrPin8)>threshA8+250){
    /* fire event */ Serial.println(15);
    delay(1500);
    threshA8=analogRead(ldrPin8);
  }else if(threshA9-250>analogRead(ldrPin9) || analogRead(ldrPin9)>threshA9+250){
    /* fire event */ Serial.println(7);
    delay(1500);
    threshA9=analogRead(ldrPin9);
  }else if(threshA10-250>analogRead(ldrPin10) || analogRead(ldrPin10)>threshA10+250){
    /* fire event */ Serial.println(11);
    delay(1500);
    threshA10=analogRead(ldrPin10);
  }else if(threshA11-250>analogRead(ldrPin11) || analogRead(ldrPin11)>threshA11+250){
    /* fire event */ Serial.println(3);
    delay(1500);
    threshA11=analogRead(ldrPin11);
  }else if(threshA12-250>analogRead(ldrPin12) || analogRead(ldrPin12)>threshA12+250){ 
    /* fire event */ Serial.println(16);
    delay(1500);
    threshA12=analogRead(ldrPin12);
  }else if(threshA13-250>analogRead(ldrPin13) || analogRead(ldrPin13)>threshA13+250){
    /* fire event */ Serial.println(8);
    delay(1500);
    threshA13=analogRead(ldrPin13);
  }else if(threshA14-250>analogRead(ldrPin14) || analogRead(ldrPin14)>threshA14+250){
    /* fire event */ Serial.println(4);
    delay(1500);
    threshA14=analogRead(ldrPin14);
  }else if(threshA15-250>analogRead(ldrPin15) || analogRead(ldrPin15)>threshA15+250){
    /* fire event */ Serial.println(12);
    delay(1500);
    threshA15=analogRead(ldrPin15);
    }else{ /* handles no changes */
      
    }
  }
