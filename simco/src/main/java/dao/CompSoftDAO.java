package dao;

public class CompSoftDAO
{
	public static boolean insert(int idsoftware) throws Exception
	{
		try
		{
			String subQuery = "(SELECT idcomputador FROM simco.computador "
							+ "order by idcomputador DESC "
							+ "LIMIT 1)";
			
			String sql = "INSERT INTO simco.compsoft(idcomputador, idsoftware) "
					   + "VALUES (" + subQuery + ", " + idsoftware + ")";

			int n = Conexao.getStatement().executeUpdate(sql);
			
			return n == 1;
		}
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return false;
	}
}
