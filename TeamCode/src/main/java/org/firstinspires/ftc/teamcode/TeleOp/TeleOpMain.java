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

            double joystickX = gamepad1.left_stick_x;
            double joystickY = gamepad1.left_stick_y;

            joystickY = -(joystickY); // invert y-axis (forward= +, backward= -)

            if (Math.abs(joystickX) < 0.10) { // create deadzone for x-axis
                joystickX = 0;
            }
            if (Math.abs(joystickY) < 0.10) { // create deadzone for y-axis
                joystickY = 0;
            }

            double defaultSpeed = 0.75;
            double motorPower = joystickY * defaultSpeed;
            double strafePower = joystickX * defaultSpeed;

            if (Math.abs(joystickY) > 0.10) {
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

            if (Math.abs(joystickX) > 0.10) {
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
        }

    }
}
