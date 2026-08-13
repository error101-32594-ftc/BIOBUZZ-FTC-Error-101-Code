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

            // Forward movement condition
            if (gamepad1.left_stick_y <= -0.5 && gamepad1.left_stick_y >= -1.0) {
                robot.leftRear.setPower(0.75);
                robot.rightRear.setPower(0.75);
                robot.leftFront.setPower(0.75);
                robot.rightFront.setPower(0.75);

            // Backward movement condition
            } else if (gamepad1.left_stick_y >= 0.5 && gamepad1.left_stick_y <= 1.0) {
                robot.leftRear.setPower(-0.75);
                robot.rightRear.setPower(-0.75);
                robot.leftFront.setPower(-0.75);
                robot.rightFront.setPower(-0.75);
            }

            // Move left condition
            if (gamepad1.left_stick_x <= -0.5 && gamepad1.left_stick_x >= -1.0) {
                robot.leftRear.setPower(-0.75); // reverse
                robot.rightRear.setPower(0.75);
                robot.leftFront.setPower(0.75);
                robot.rightFront.setPower(-0.75); // reverse

            // Move right condition
            } else if (gamepad1.left_stick_x >= 0.5 && gamepad1.left_stick_x <= 1.0) {
                robot.leftRear.setPower(0.75);
                robot.rightRear.setPower(-0.75); // reverse
                robot.leftFront.setPower(-0.75); // reverse
                robot.rightFront.setPower(0.75);
            }
        }

    }
}
