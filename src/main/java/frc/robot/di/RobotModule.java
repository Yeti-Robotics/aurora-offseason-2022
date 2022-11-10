package frc.robot.di;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import dagger.Module;
import dagger.Provides;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class RobotModule {
    @Provides
    @Singleton
    public RobotContainer providesRobotContainer(
        DrivetrainSubsystem drivetrainSubsystem) {
        return new RobotContainer(
            drivetrainSubsystem
        );
    }


    /****** Drivetrain Subsystem Dependencies ******/
    @Provides
    @Singleton
    @Named("left drive motors")
    public MotorControllerGroup provideLeftMotors(
        @Named("left drive motor 1") WPI_TalonFX motorOne,
        @Named("left drive motor 2") WPI_TalonFX motorTwo) {
        return new MotorControllerGroup(motorOne, motorTwo);
    }

    @Provides
    @Singleton
    @Named("right drive motors")
    public MotorControllerGroup provideRightMotors(
        @Named("right drive motor 1") WPI_TalonFX motorOne,
        @Named("right drive motor 2") WPI_TalonFX motorTwo) {
        MotorControllerGroup motorGroup = new MotorControllerGroup(motorOne, motorTwo);
        motorGroup.setInverted(true);
        return motorGroup;
    }

    @Provides
    @Singleton
    public DifferentialDrive provideDifferentialDrive(
        @Named("left drive motors") MotorControllerGroup leftMotors,
        @Named("right drive motors") MotorControllerGroup rightMotors) {
        return new DifferentialDrive(leftMotors, rightMotors);
    }

    @Provides
    @Singleton
    public DifferentialDriveWheelSpeeds provideDifferentialWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds();
    }

    @Provides
    @Singleton
    public DifferentialDriveOdometry provideDifferentialDriveOdometry(AHRS gyro) {
        return new DifferentialDriveOdometry(gyro.getRotation2d());
    }
}
