/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.Drive.ToggleBackJack;
import frc.robot.commands.Drive.ToggleFrontJack;
import frc.robot.commands.Lift.CalibrateLift;
import frc.robot.commands.Wrist.IntakeIn;
import frc.robot.commands.Wrist.IntakeOut;
import frc.robot.commands.Wrist.ToggleGripper;
import frc.robot.commands.Wrist.ToggleHatchLifter;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

  public static OI oi;

  public static Drive drive;
  public static Lift lift;
  public static Arm arm;
  public static Wrist wrist;

  private Compressor compressor;

  @Override
  public void robotInit() {
    
    // try {
    //   CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_ONE);
    // } catch (Exception e) {
    //   System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_ONE);
    // }

    // try {
    //   CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_TWO);
    // } catch (Exception e) {
    //   System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_TWO);
    // }

    oi = new OI();

    drive = new Drive();
    lift = new Lift();
    arm = new Arm();
    wrist = new Wrist();

    // SmartDashboard.putData(drive);
    // SmartDashboard.putData(lift);
    // SmartDashboard.putData(arm);
    // SmartDashboard.putData(wrist);

    compressor = new Compressor();
    compressor.setClosedLoopControl(RobotMap.Config.ENABLE_PNEUMATICS);

    System.out.println("Robot has turned on");
  }

  private void commonInit() {
    oi.joystick.a.whenPressed(new ToggleHatchLifter());
    oi.joystick.b.whenPressed(new ToggleGripper());
    
    oi.joystick.x.whenPressed(new ToggleFrontJack());
    oi.joystick.y.whenPressed(new ToggleBackJack());

    oi.joystick.start.whenPressed(new CalibrateLift());

    oi.joystick.lefJoystickButton.whenPressed(new IntakeOut());
    oi.joystick.righJoystickButton.toggleWhenPressed(new IntakeIn());
  }

  private void commonLoop() {
    Scheduler.getInstance().run();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    commonInit();
  }

  @Override
  public void autonomousPeriodic() {
    commonLoop();
  }

  @Override
  public void teleopInit() {
    commonInit();
  }

  @Override
  public void teleopPeriodic() {
    commonLoop();
  }

  @Override
  public void testPeriodic() {
  }
}
