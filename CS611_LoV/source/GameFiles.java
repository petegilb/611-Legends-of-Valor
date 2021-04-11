
package lmh;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

// Loads the game files into memory. Uses a singleton model, because
// there is no need to load the game files more than once, so it must be avoided.
// Uses an inner class to work with files.
public final class GameFiles
{
  private final static GameFiles instance = new GameFiles();
  private final String FILE_DIR = "./GameFiles";
  private ArrayList<Object> allObjects;

  private GameFiles()
  {
    allObjects = new ArrayList<Object>();
    File dir = new File (FILE_DIR);

    for (File file: dir.listFiles())
    {
      String fileName = file.getName();
      String className = "lmh.";
      LoadFile lf = new LoadFile (FILE_DIR + "/" + fileName);
      fileName = fileName.substring (0, fileName.length() - 5);

      lf.openFile();
      lf.getNextLine();

      switch (fileName)
      {
        case "Dragon":
        case "Exoskeleton":
        case "Spirit":
        case "Warrior":
        case "Paladin":
        case "Sorcerer":
          className += "creatures.";
          break;
        default:
          className += "items.";
      }

      className += fileName;
      Constructor cons = null;

      try
      {
        cons = Class.forName (className).getDeclaredConstructor (String[].class);

        for (String line; (line = lf.getNextLine()) != null; )
        {
          String[] attributes = line.split("\\s+");
          allObjects.add (cons.newInstance (new Object[]{ attributes }));
        }
      }
      catch (Exception e)
      {
        System.out.println (e);
      }
      lf.closeFile();
    }
  }

  public static GameFiles getInstance()
  {
    return instance;
  }

  public ArrayList<Object> getAllObjects ()
  {
    return allObjects;
  }

  // An inner class for handling file I/O.
  private class LoadFile
  {
    String fileName;
    BufferedReader br;

    public LoadFile ()
    {
			System.out.println("Try calling with a file name");
    }

    public LoadFile (String fileName)
    {
      this.fileName = fileName;
    }

    public void openFile ()
    {
      try
      {
        br = new BufferedReader(new FileReader(fileName));
  		}
      catch (Exception e)
      {
  			System.out.println(e);
  		}
    }

    public String getNextLine ()
    {
      try
      {
        return br.readLine();
  		}
      catch (Exception e)
      {
  			System.out.println("Can't read the file: " + fileName);
  		}
      return null;
    }

    public void closeFile ()
    {
      try
      {
  			br.close();
  		}
      catch (Exception e)
      {
  			System.out.println("Can't close the file: " + fileName);
  		}
    }
  }
}
