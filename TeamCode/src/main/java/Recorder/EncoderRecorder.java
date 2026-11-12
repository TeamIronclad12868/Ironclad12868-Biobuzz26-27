package Recorder;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class EncoderRecorder {

    DcMotor FL;
    DcMotor FR;
    DcMotor RL;
    DcMotor RR;
    DcMotor arm;
    DcMotor slide;


    boolean StopRecord;
    Telemetry telemetry;



    public EncoderRecorder(DcMotor FL, DcMotor FR, DcMotor RL, DcMotor RR, DcMotor arm,DcMotor slide, Telemetry telemetry) {


        this.FL = FL;
        this.FR = FR;
        this.RL = RL;
        this.RR = RR;
        this.arm = arm;
        this.slide = slide;
        this.telemetry = telemetry;
        reset();
    }
    public EncoderRecorder( DcMotor arm,DcMotor slide, Telemetry telemetry) {

        this.arm = arm;
        this.slide = slide;
        this.telemetry = telemetry;
        reset2();
    }
    public EncoderRecorder(DcMotor FL, DcMotor FR, DcMotor RL, DcMotor RR, Telemetry telemetry) {


        this.FL = FL;
        this.FR = FR;
        this.RL = RL;
        this.RR = RR;
        this.telemetry = telemetry;
        reset1();
    }
    public void run(int FL, int FR, int RL, int RR,int arm,int slide, double power, int accuracy, int successRate) {
        reset();
        while (!isOnTarget( FL, FR, RL, RR, arm,slide, accuracy, successRate)&& isOpModeActive()) {
            ;
            this.FL.setTargetPosition(FL);
            this.FR.setTargetPosition(FR);
            this.RL.setTargetPosition(RL);
            this.RR.setTargetPosition(RR);
            this.arm.setTargetPosition(arm);
            this.slide.setTargetPosition(slide);


            this.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.RL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            this.FL.setPower(power);
            this.FR.setPower(power);
            this.RL.setPower(power);
            this.RR.setPower(power);
            this.arm.setPower(power);
            this.slide.setPower(power);


            output(this.FL.getCurrentPosition(), this.FR.getCurrentPosition(), this.RL.getCurrentPosition(), this.RR.getCurrentPosition(), this.arm.getCurrentPosition(), this.slide.getCurrentPosition());
        }
    }
    public void run(int arm,int slide, double power, int accuracy, int successRate) {
        reset2();
        while (!isOnTarget( arm,slide, accuracy, successRate)&& isOpModeActive()) {

            this.arm.setTargetPosition(arm);
            this.slide.setTargetPosition(slide);


            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            this.arm.setPower(power);
            this.slide.setPower(power);


            output(this.FL.getCurrentPosition(), this.FR.getCurrentPosition(), this.RL.getCurrentPosition(), this.RR.getCurrentPosition(), this.arm.getCurrentPosition(), this.slide.getCurrentPosition());
        }
    }
    public void run(int FL, int FR, int RL, int RR, double power, int accuracy, int successRate) {
        reset1();
        while (!isOnTarget( FL, FR, RL, RR, accuracy, successRate)&& isOpModeActive()) {
            ;
            this.FL.setTargetPosition(FL);
            this.FR.setTargetPosition(FR);
            this.RL.setTargetPosition(RL);
            this.RR.setTargetPosition(RR);


            this.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.RL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            this.FL.setPower(power);
            this.FR.setPower(power);
            this.RL.setPower(power);
            this.RR.setPower(power);


            output(this.FL.getCurrentPosition(), this.FR.getCurrentPosition(), this.RL.getCurrentPosition(), this.RR.getCurrentPosition());
        }
    }

    public void run(int FL, int FR, int RL, int RR,int arm,int slide, double power, int accuracy) {
        run(FL, FR, RL, RR, arm,slide, power, accuracy, 2);
    }
    public void run(int FL, int FR, int RL, int RR, double power, int accuracy) {
        run(FL, FR, RL, RR, power, accuracy, 2);
    }
    public void run(int arm,int slide, double power, int accuracy) {
        run( arm,slide, power, accuracy, 2);
    }

    private boolean isOpModeActive() {
        return true;
    }

    boolean recordFirstRun = false;
    boolean stopRecord;
    //drive robot and record encoder pos
    public void record(double FL, double FR, double RL, double RR, double arm,double slide, boolean stop) throws InterruptedException {
        int[] data = new int[6];
        if(!recordFirstRun) {
            recordFirstRun = true;
            stopRecord = false;
            reset();
        }

        if(!stopRecord) {
            data[0] = runMotor(this.FL, FL);
            data[1] = runMotor(this.FR, FR);
            data[2] = runMotor(this.RL, RL);
            data[3] = runMotor(this.RR, RR);
            data[4] = runMotor(this.arm, arm);
            data[5] = runMotor(this.slide, slide);


            if(stop) stopRecord = true;
        } else {
            recordFirstRun = false;
            while(true) {
                data[0] = runMotor(this.FL, 0);
                data[1] = runMotor(this.FR, 0);
                data[2] = runMotor(this.RL, 0);
                data[3] = runMotor(this.RR, 0);
                data[4] = runMotor(this.arm, 0);
                data[5] = runMotor(this.slide, 0);


                Thread.sleep(50);
            }
        }
        output(data[0], data[1], data[2], data[3], data[4], data[5]);
    }
    public void record(double FL, double FR, double RL, double RR, boolean stop) throws InterruptedException {
        int[] data = new int[4];
        if(!recordFirstRun) {
            recordFirstRun = true;
            stopRecord = false;
            reset1();
        }

        if(!stopRecord) {
            data[0] = runMotor(this.FL, FL);
            data[1] = runMotor(this.FR, FR);
            data[2] = runMotor(this.RL, RL);
            data[3] = runMotor(this.RR, RR);


            if(stop) stopRecord = true;
        } else {
            recordFirstRun = false;
            while(true) {
                data[0] = runMotor(this.FL, 0);
                data[1] = runMotor(this.FR, 0);
                data[2] = runMotor(this.RL, 0);
                data[3] = runMotor(this.RR, 0);


                Thread.sleep(50);
            }
        }
        output(data[0], data[1], data[2], data[3]);
    }
    public void record( double arm,double slide, boolean stop) throws InterruptedException {
        int[] data = new int[2];
        if(!recordFirstRun) {
            recordFirstRun = true;
            stopRecord = false;
            reset2();
        }

        if(!stopRecord) {
            data[0] = runMotor(this.arm, arm);
            data[1] = runMotor(this.slide, slide);


            if(stop) stopRecord = true;
        } else {
            recordFirstRun = false;
            while(true) {
                data[0] = runMotor(this.arm, 0);
                data[1] = runMotor(this.slide, 0);


                Thread.sleep(50);
            }
        }
        output(data[0], data[1]);
    }

    //run a single motor and return encoder pos
    private int runMotor(DcMotor motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(power);
        return motor.getCurrentPosition();
    }


    //reset all encoder pos
    public void reset() {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }
    public void reset1() {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void reset2() {
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    private void output(int FL, int FR, int RL, int RR, int arm, int slide) { //i hate the telemetry class
        telemetry.addData("FL pos", FL);
        telemetry.addData("FR pos", FR);
        telemetry.addData("RL pos", RL);
        telemetry.addData("RR pos", RR);
        telemetry.addData("arm pos", arm);
        telemetry.addData("slide pos", slide);


        telemetry.update();
    }
    private void output(int FL, int FR, int RL, int RR) { //i hate the telemetry class
        telemetry.addData("FL pos", FL);
        telemetry.addData("FR pos", FR);
        telemetry.addData("RL pos", RL);
        telemetry.addData("RR pos", RR);


        telemetry.update();
    }
    private void output( int arm, int slide) { //i hate the telemetry class
        telemetry.addData("arm pos", arm);
        telemetry.addData("slide pos", slide);


        telemetry.update();
    }

    private boolean isOnTarget(int targ1, int targ2, int targ3, int targ4, int targ5, int targ6, int accuracy, int successRate) {
        int success = 0;
        if (Math.abs(this.FL.getCurrentPosition() - targ1) <= accuracy) success++;
        if (Math.abs(this.FR.getCurrentPosition() - targ2) <= accuracy) success++;
        if (Math.abs(this.RL.getCurrentPosition() - targ3) <= accuracy) success++;
        if (Math.abs(this.RR.getCurrentPosition() - targ4) <= accuracy) success++;
        if (Math.abs(this.arm.getCurrentPosition() - targ5) <= accuracy) success++;
        if (Math.abs(this.slide.getCurrentPosition() - targ6) <= accuracy) success++;


        return success >= successRate;
    }
    private boolean isOnTarget(int targ1, int targ2, int targ3, int targ4, int accuracy, int successRate) {
        int success = 0;
        if (Math.abs(this.FL.getCurrentPosition() - targ1) <= accuracy) success++;
        if (Math.abs(this.FR.getCurrentPosition() - targ2) <= accuracy) success++;
        if (Math.abs(this.RL.getCurrentPosition() - targ3) <= accuracy) success++;
        if (Math.abs(this.RR.getCurrentPosition() - targ4) <= accuracy) success++;


        return success >= successRate;
    }

    private boolean isOnTarget(int targ1, int targ2, int accuracy, int successRate) {
        int success = 0;
        if (Math.abs(this.arm.getCurrentPosition() - targ1) <= accuracy) success++;
        if (Math.abs(this.slide.getCurrentPosition() - targ2) <= accuracy) success++;


        return success >= successRate;
    }
}
