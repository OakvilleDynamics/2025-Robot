package frc.robot.misc;

import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;

public class Alerts {
  // Git alert for when there are uncommitted changes in the repo
  public static Alert gitDirtyAlert =
      new Alert(
          "There are uncommitted changes made to the repository! Please create a commit with all changes as soon as possible!",
          AlertType.kWarning);

  // Git alert for when you are not on the "main" branch
  public static Alert gitMainBranchAlert =
      new Alert(
          "You are not on the \"main\" branch. Please merge your changes with the \"main\" branch as soon as possible.",
          AlertType.kWarning);

  // Git alert for when you are on an event branch
  public static Alert gitEventBranchAlert =
      new Alert(
          "You are on an \"event\" branch. Please test any new code on a practice field, and merge changes into \"main\" when possible.",
          AlertType.kInfo);
}
