# SlowLoris


CS585 Project/Research


The goal of this project and research, was to research DDoS attacks and what makes them so difficult to defend against, and if there was potential ways to stop one.

I decided to do an interesting attack called Slow Loris which is a layer 7 attack, which is one of the hardest to identify and defend against. The reason why Slow Loris is hard to defend against is because it will be replicating natural behaviors of regular users. The way this attacks works is by taking up all the sockets and sending incomplete http request to an apache server. By doing so the server is waiting for the user to finish sending the request but the program will just slowly send little amounts of information, simulating a slow connection, but in reality we are tricking the server to think that the connection is slow. Slow Loris is very dangerous because you only need one computer rather then a cluster of computers to actually be successful in an attack. Of course by only using one computer, it is easily trackable, but the postive of using this attack is that it takes efficient, It does not take up much of your own bandwidth allowing you to continue doing daily task while running the attack in the background. The way I tested this attack is by setting up a local apache server on a VM. The reason why an apache server is necessary is because it was easily configured, and there are a couple of features that allows better display of ddos attack in effect. As I stated earlier this is a Layer 7 attack which is an attack on the application level there are ways to stop these types of attack if only one user, by black listing their Ip but if the attacker uses a cluster of computers and proxies it would be very difficult to track and halt the attack.





