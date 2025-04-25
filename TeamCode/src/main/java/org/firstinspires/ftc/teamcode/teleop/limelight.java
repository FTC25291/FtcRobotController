public class Limelight {

    Limelight3A limelight;

    private double tx;
    private double ty;
    private double ta;

    @Override
    public void runOpMode() throws InterruptedException {

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();

        limelight.pipelineSwitch(0);

        waitForStart();

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                tx = result.getTx(); // How far left or right the target is (degrees)
                ty = result.getTy(); // How far up or down the target is (degrees)
                ta = result.getTa(); // How big the target looks (0%-100% of the image)
            }
        }
    }
}