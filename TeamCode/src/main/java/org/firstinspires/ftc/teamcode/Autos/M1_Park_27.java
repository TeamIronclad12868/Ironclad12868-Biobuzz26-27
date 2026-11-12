package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import Recorder.EncoderRecorder;

@Autonomous(name="M1_Park_27", group="Robot")
//@Disabled
public class  M1_Park_27 extends LinearOpMode {

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
    public final int LAUNCHER_TARGET_VELOCITY = 1250; // RPM=2785 when vel = 1300
    // rpm=(velocity/ticks per rev(28))x60
    public final int LAUNCHER_MIN_VELOCITY = 1200; // RPM=  2678 when vel = 1250)
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


                recorder.run(-58, 45, -58, 43, .3, 5, 3);//backward
                recorder.run(50, 50, 50, 50, .5, 10, 3);//turn right

                launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
                sleep(10000);
                windmill.setPower(-.75);//all on
                intakeTop.setPower(1);
                intakeL.setPower(-1);
                intakeR.setPower(1);

                advancement.setPower(-1);//launch 1
                sleep(250);
                advancement.setPower(0);//pause
                sleep(750);
                advancement.setPower(-1);//launch 2
                sleep(250);
                advancement.setPower(0);//pause
                sleep(750);
                advancement.setPower(-1);//launch 3
                sleep(250);
                advancement.setPower(0);//pause
                sleep(750);
                advancement.setPower(-1);//launch 4
                sleep(450);
                advancement.setPower(0);//pause
                sleep(750);
                advancement.setPower(-1);//launch 5
                sleep(450);





                windmill.setPower(0);//all off
                advancement.setPower(0);
                intakeTop.setPower(0);
                intakeL.setPower(0);
                intakeR.setPower(0);

                launcher.setVelocity(0);

                recorder.run(-368, 351, -354, 346, .5, 10, 3);//backward
                recorder.run(912, 887, 888, 867, .5, 10, 3);//turn right
                recorder.run(-2094, 2089, -2085, 2060, .6, 10, 3);//backward
                //continue code


                sleep(999999999);

                telemetry.update();
                stop();

            }
        }
    }
}

