import me.m_3.defaultCore.Main;
import me.m_3.tiqoL.WSServer;
import me.m_3.tiqoL.coreloader.Core;
import me.m_3.tiqoL.coreloader.interfaces.ClassPointer;

public class Pointer implements ClassPointer{

	public Core getCore(WSServer server) {
		return new Main(server , "Default Core");
	}
	
}
