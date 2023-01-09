package proyectoindividual;
import java.sql.Connection;
public class ProyectoIndividual
{
  private java.sql.Connection conexion;
  private java.sql.Statement sentenciaSQL;
  private java.sql.ResultSet cdr;
    private Connection n;
  
  public ProyectoIndividual() throws ClassNotFoundException, java.sql.SQLException,
      InstantiationException, IllegalAccessException
  {
    String controlador = "com.mysql.cj.jdbc.Driver";
    Class.forName(controlador).newInstance();
    conectar(); 
  }
  public void conectar() throws java.sql.SQLException
  {
    String URL_bd = "jdbc:mysql://127.0.0.1:3306/proyecto1";
    String usuario = "root";
    String pw = "";
    conexion = java.sql.DriverManager.getConnection(
        URL_bd, usuario, pw);
    sentenciaSQL = conexion.createStatement(
                       java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
                       java.sql.ResultSet.CONCUR_UPDATABLE);
    System.out.println("\nConexion realizada con exito.\n");
    System.out.println("Tablas de la base de datos: ");
    String[] tabla = tablas();
    for (int i = 0; i < tabla.length; ++i)
      System.out.println(tabla[i]);
  }
  public void cerrarConexion() throws java.sql.SQLException
  {
    if (cdr != null) cdr.close();
    if (sentenciaSQL != null) sentenciaSQL.close();
    if (conexion != null) conexion.close();
  }
  public String[] tablas() throws java.sql.SQLException
  {
    cdr = sentenciaSQL.executeQuery("SHOW TABLES");
    cdr.last();
    String[] tablas = new String[cdr.getRow()];
    cdr.beforeFirst();
    int i = 0;
    while(cdr.next())
      tablas[i++] = cdr.getString(1);
    return tablas;
  }
  public java.sql.ResultSet obtenerTabla(String tabla) throws java.sql.SQLException  {
    cdr = sentenciaSQL.executeQuery(
        "SELECT * FROM " + tabla);
    return cdr;
  }  
  public void insertarFilaEnPersonajes(String nombre, int rareza, String elemento, String arma)
      throws java.sql.SQLException
  {
    sentenciaSQL.executeUpdate("INSERT INTO " + "PERSONAJES" +
      " VALUES ('" + nombre + "', " + rareza + ", '" + elemento + "', '" + arma + "')"
    );
  }
  public void insertarFilaEnArmas(String nombre, int rareza, String tipo)
      throws java.sql.SQLException
  {
    sentenciaSQL.executeUpdate("INSERT INTO " + "ARMAS" +
      " VALUES ('" + nombre + "', " + rareza + ", '" + tipo + "')"
    );
  }
  public void borrarFilaEnPersonajes(String nombre)
      throws java.sql.SQLException
  {
    sentenciaSQL.executeUpdate("DELETE FROM " + "PERSONAJES" +
       " WHERE NOMBRE LIKE '" + nombre+"'");
  }
  public void borrarFilaEnArmas(String nombre)
      throws java.sql.SQLException
  {
    sentenciaSQL.executeUpdate("DELETE FROM " + "ARMAS" +
       " WHERE NOMBRE LIKE '" + nombre+"'");
  }
  public void mostrarTabla(String tabla)
      throws java.sql.SQLException
  {
    cdr = obtenerTabla(tabla);
    while(cdr.next()) mostrarFilaActual();
  }
  public void mostrarFilaActual() throws java.sql.SQLException
  {
    int nColumnas = cdr.getMetaData().getColumnCount();
    for (int i = 1; i <= nColumnas; ++i)
    {
      System.out.print(cdr.getString(i) + " ");
    }
    System.out.println();
  } 
}