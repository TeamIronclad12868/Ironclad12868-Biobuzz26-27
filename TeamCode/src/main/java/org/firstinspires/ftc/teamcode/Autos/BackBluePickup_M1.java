package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Recorder.EncoderRecorder;

@Autonomous(name="BackBluePickup_M1", group="Robot")
@Disabled
public class BackBluePickup_M1 extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotorEx launcher;

    private DcMotor intakeTop = null;
    private CRServo intakeL = null;
    private CRServo intakeR = null;
    private DcMotor windmill = null;
    EncoderRecorder recorder;
    private DcMotor advancement=null;
    public final int LAUNCHER_TARGET_VELOCITY = 1300; // RPM=2785 when vel = 1300
    // rpm=(velocity/ticks per rev(28))x60
    public final int LAUNCHER_MIN_VELOCITY = 1250; // RPM=  2678 when vel = 1250)
    // velocity=(rpm/60)xticks per rev(28)

    //@Override
    public void runOpMode() {

        // Initialize the drive system variables.
        FL = hardwareMap.get(DcMotor.class, "fl");
        FR = hardwareMap.get(DcMotor.class, "fr");
        BL = hardwareMap.get(DcMotor.class, "bl");
        BR = hardwareMap.get(DcMotor.class, "br");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        intakeTop = hardwareMap.get(DcMotor.class, "intakeTop");
        windmill = hardwareMap.get(DcMotor.class, "windmill");
        intakeL = hardwareMap.get(CRServo.class, "intakeL");
        intakeR = hardwareMap.get(CRServo.class, "intakeR");
        advancement = hardwareMap.get(DcMotor.class, "advancement");

        recorder = new EncoderRecorder(FL, FR, BL, BR, telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        {

            while (opModeIsActive()) { // Add a loop to continually update telemetry


                recorder.run(-53, 50, -54, 51, .3, 5, 3);//backward


                launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);//lanches
                sleep(5000);
                windmill.setPower(-1);
                advancement.setPower(-1);
                intakeTop.setPower(1);
                intakeL.setPower(1);
                intakeR.setPower(-1);
                sleep(6000);
                windmill.setPower(0);
                advancement.setPower(0);
                intakeTop.setPower(0);
                intakeL.setPower(0);
                intakeR.setPower(0);
                launcher.setVelocity(0);

                recorder.run(-775, 778, -777, 764, 1, 10, 3);//backward
                recorder.run(611, 596, 581, 593, .7, 10, 3);//turn right
                recorder.run(1417, -1414, 1402, -1402, .9, 10, 3);//forward

                intakeTop.setPower(1);//intake on
                intakeL.setPower(-1);
                intakeR.setPower(1);
                windmill.setPower(1);

                recorder.run(361, -365, 330, -387, .4, 10, 3);//forward
                recorder.run(178, 170, 170, 176, .3, 10, 3);//turn right
                recorder.run(1073, -874, 1080, -837, .3, 10, 3);//forward
                sleep(1000);

                recorder.run(-2035, 2030, -2032, 2012, 1, 10, 3);//backward
                intakeTop.setPower(0);//intake off
                intakeL.setPower(0);
                intakeR.setPower(0);
                windmill.setPower(0);
                recorder.run(-1005, -999, -1008, -997, .8, 10, 3);//turn left
                recorder.run(85, -80, 82, -78, .8, 10, 3);//forward


                launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);//lanches
                sleep(1000);
                windmill.setPower(-1);
                advancement.setPower(-1);
                intakeTop.setPower(1);
                intakeL.setPower(1);
                intakeR.setPower(-1);
                sleep(6000);
                windmill.setPower(0);
                advancement.setPower(0);
                intakeTop.setPower(0);
                intakeL.setPower(0);
                intakeR.setPower(0);
                launcher.setVelocity(0);







                //continue code


                sleep(999999999);

                telemetry.update();
                stop();

            }
        }
    }
}

