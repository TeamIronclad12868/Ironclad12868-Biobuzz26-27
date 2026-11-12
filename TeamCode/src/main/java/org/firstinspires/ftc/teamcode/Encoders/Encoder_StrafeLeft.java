package org.firstinspires.ftc.teamcode.Encoders;



import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import Recorder.EncoderRecorder;

//import Recorder.EncoderRecorder;


@TeleOp(name="Encoder_StrafeLeft", group="Encoder")
@Disabled
public class Encoder_StrafeLeft extends LinearOpMode {

    DcMotor BL;
    DcMotor BR;
    DcMotor FL;
    DcMotor FR;


    EncoderRecorder recorder;

    @Override
    public void runOpMode() throws InterruptedException {
        FL = hardwareMap.get(DcMotor.class, "fl");
        FR = hardwareMap.get(DcMotor.class, "fr");
        BL = hardwareMap.get(DcMotor.class, "bl");
        BR = hardwareMap.get(DcMotor.class, "br");


        recorder = new EncoderRecorder(FL,FR,BL,BR, telemetry);
        waitForStart();
        if (opModeIsActive()) {
              while(true){
                        exampleRecordingCode:recorder.record(-0.1, -0.1,0.1,0.1, gamepad1.a);
                      }
            //recorder.run(5000, -5000, 5000, -5000, 1, 1);
           //recorder.run(5000, 5000, -5000, 5000, 1, 1);

        }
    }
}
