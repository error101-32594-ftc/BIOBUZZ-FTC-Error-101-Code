// This is the main java class for the TeleOp controller-based code of the robot
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Main")
public class TeleOpMain extends LinearOpMode {
    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Robot Initialized:", "True");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Sets up joystick & corr. motor movement for DRIVE controller

            double leftJoystickX = gamepad1.left_stick_x;
            double leftJoystickY = gamepad1.left_stick_y;
            double rightJoystickX = gamepad1.right_stick_x;

            leftJoystickY = -(leftJoystickY); // invert y-axis (forward= +, backward= -)

            if (Math.abs(leftJoystickX) < 0.10) { // create deadzone for x-axis
                leftJoystickX = 0;
            }
            if (Math.abs(leftJoystickY) < 0.10) { // create deadzone for y-axis
                leftJoystickY = 0;
            }

            double defaultSpeed = 0.75;
            double motorPower = leftJoystickY * defaultSpeed;
            double strafePower = leftJoystickX * defaultSpeed;
            double turnPower = rightJoystickX * defaultSpeed;

            if (Math.abs(leftJoystickY) > 0.10) {
                robot.rightRear.setPower(motorPower);
                robot.leftRear.setPower(motorPower);
                robot.rightFront.setPower(motorPower);
                robot.leftFront.setPower(motorPower);
            } else {
                robot.rightRear.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightFront.setPower(0);
                robot.leftFront.setPower(0);
            }

            if (Math.abs(leftJoystickX) > 0.10) {
                robot.leftRear.setPower(-strafePower); // reverse
                robot.rightRear.setPower(strafePower);
                robot.leftFront.setPower(strafePower);
                robot.rightFront.setPower(-strafePower); // reverse
            } else {
                robot.rightRear.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightFront.setPower(0);
                robot.leftFront.setPower(0);
            }

            if (Math.abs(rightJoystickX) > 0.10) { // for left & right turns in place
                robot.leftRear.setPower(robot.leftRear.getPower() + turnPower);
                robot.leftFront.setPower(robot.leftFront.getPower() + turnPower);
                robot.rightRear.setPower(robot.rightRear.getPower() - turnPower);
                robot.rightFront.setPower(robot.rightFront.getPower() - turnPower);
            } else {
                robot.rightRear.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightFront.setPower(0);
                robot.leftFront.setPower(0);
            }
        }

    }
}
