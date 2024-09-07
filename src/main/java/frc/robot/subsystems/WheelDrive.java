// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private CANSparkMax angleMotor;
  private CANSparkMax speedMotor;
  private DutyCycleEncoder encoder;
  private PIDController pidController;

  public WheelDrive(int angleMotor, int speedMotor, int encoder) {
    this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
    this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

    this.encoder = new DutyCycleEncoder(encoder);
    this.encoder.setDistancePerRotation(360);

    pidController = new PIDController(1, 0, 0);
    pidController.enableContinuousInput(0, 360);
    //pidController.setSetpoint(pidController.calculate(this.encoder.getAbsolutePosition(), this.angleMotor.get()));
  }

  public void drive(double speed, double angle){
    speedMotor.set(speed);
    //aqui es donde se realciona PID motor con encoder
    angleMotor.set(pidController.calculate(encoder.getAbsolutePosition(), angle*360));

    //pidController.setSetpoint(angle*360);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
