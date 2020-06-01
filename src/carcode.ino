/*
  Description: A simple Car control Code which uses bluetooth and an android to control the Car.
*/

#include <Servo.h>
Servo servoLeft;          // Define left servo
Servo servoRight;         // Define right servo
int Received = 0; 		// will hold the value we recieve from the phone connected via bluetooth
void setup() {
  Serial.begin(9600);
  servoLeft.attach(10);  // Set left servo to digital pin 10
  servoRight.attach(9);  // Set right servo to digital pin 9
}

void loop() {
  if (Serial.available() > 0) //checks if Bluetooth singal can be received
  {
    Received = Serial.read(); // sets the value sent from the phone to the variable Received
    Serial.println(Serial.read());

  }
  if (Received == '1') { // makes the car go forward
    forward();
  }
  else if (Received == '2') { // makes the car go backwards
    reverse();
  }
  else if (Received == '3') { // makes the car turn left
    turnLeft();
  }
  else if (Received == '4') { // makes the car turn right
    turnRight();
  }
  else if (Received == '5') { // makes the car stop
    stopRobot();
  }
}

// Motion routines for forward, reverse, turns, and stop
void forward() {
  servoLeft.write(0);
  servoRight.write(180);
}

void reverse() {
  servoLeft.write(180);
  servoRight.write(0);
}

void turnRight() {
  servoLeft.write(180);
  servoRight.write(180);
}
void turnLeft() {
  servoLeft.write(0);
  servoRight.write(0);
}

void stopRobot() {
  servoLeft.write(90);
  servoRight.write(90);
}
