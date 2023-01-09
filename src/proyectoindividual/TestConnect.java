package proyectoindividual;
public class TestConnect
{
  private static ProyectoIndividual Base;
  
  public TestConnect() throws ClassNotFoundException,
      java.sql.SQLException, InstantiationException,
      IllegalAccessException
  {
    Base = new ProyectoIndividual();
  }
  public void insertarFilaEnPersonajes()
      throws java.sql.SQLException
  {
    int rareza;
    String nombre, elemento, arma;
    
    System.out.print("\nNombre: ");
    nombre = Lectura.dato();
    System.out.print("Rareza: ");
    rareza = Lectura.datoInt();
    System.out.print("Elemento: ");
    elemento = Lectura.dato();
    System.out.print("Arma: ");
    arma = Lectura.dato(); 
    Base.insertarFilaEnPersonajes(nombre, rareza, elemento, arma);
  }
  public void insertarFilaEnArmas()
      throws java.sql.SQLException
  {
    int rareza;
    String nombre, tipo;
    System.out.print("\nNombre: ");
    nombre = Lectura.dato();
    System.out.print("Rareza: ");
    rareza = Lectura.datoInt();
    System.out.print("Tipo: ");
    tipo = Lectura.dato();
    Base.insertarFilaEnArmas(nombre, rareza, tipo);
  }
  public void borrarFilaEnPersonajes()
      throws java.sql.SQLException
  {
    String nombre;
    System.out.print("\nNombre: ");
    nombre = Lectura.dato();
    Base.borrarFilaEnPersonajes(nombre);
  }
  public void borrarFilaEnArmas()
      throws java.sql.SQLException
  {
    String nombre;

    System.out.print("\nNombre: ");
    nombre = Lectura.dato();
    Base.borrarFilaEnArmas(nombre);
  } 
  public static int menu(String[] opciones, int numOpciones)
  {
    int i = 0, opcion = 0;
    
    System.out.println("\n____________________________________\n");
    for (i = 1; i <= numOpciones; ++i)
    {
      System.out.print("    " + i + ". " + opciones[i-1] + "\n");
    }
    System.out.println("____________________________________\n");
    do
    {
      System.out.print("\nOpci n (1 - " + numOpciones + "): ");
      opcion = Lectura.datoInt();
    }
    while(opcion < 1 || opcion > numOpciones);
    return opcion;
  }
  public static void main(String args[])
  {
    int i = 0, opcion = 0;
    TestConnect objAp = null;
    try
    {
      objAp = new TestConnect();

      String[] opciones = { "Datos de la tabla",
                            "Insertar fila en \"personajes\"",
                            "Insertar fila en \"armas\"",
                            "Borrar fila en \"personajes\"",
                            "Borrar fila en \"armas\"",
                            "Salir." };
      String[] tablas = Base.tablas();
      do
      {
        switch(opcion = objAp.menu(opciones, opciones.length))
        {
          case 1:
            i = objAp.menu(tablas, tablas.length);
            Base.mostrarTabla(tablas[i-1]);
            break;
          case 2:
            objAp.insertarFilaEnPersonajes();
            break;
          case 3:
            objAp.insertarFilaEnArmas();
            break;
          case 4:
            objAp.borrarFilaEnPersonajes();
            break;
          case 5:
            objAp.borrarFilaEnArmas();
            break;
        }
      }
      while (opcion != 6);
    }
    catch(ClassNotFoundException e)
    {
      System.out.println(e.getMessage());  
    }
    catch(InstantiationException e)
    {
      System.out.print(e.getMessage());
    }
    catch(IllegalAccessException e)
    {
      System.out.print(e.getMessage());
    }
    catch(java.sql.SQLException e)
    {
      System.out.print(e.getMessage());
    }
    finally
    {
      try
      {
        Base.cerrarConexion();
      }
      catch(java.sql.SQLException ignorada) {}
    }
  }
}