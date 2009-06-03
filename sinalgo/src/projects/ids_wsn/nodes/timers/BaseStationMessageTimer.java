package projects.ids_wsn.nodes.timers;
import projects.ids_wsn.nodes.messages.FloodFindDsdv;
import projects.ids_wsn.nodes.nodeImplementations.BaseStation;
import sinalgo.nodes.messages.Message;
import sinalgo.nodes.timers.Timer;

/**
 * A timer to initially send a message. This timer
 * is used in synchronous simulation mode to handle user
 * input while the simulation is not running. 
 */
public class BaseStationMessageTimer extends Timer {
	private Message msg = null; // the msg to send
	private Integer interval;

	/**
	 * @param msg The message to send
	 */
	public BaseStationMessageTimer(Message msg, Integer interval) {
		this.msg = msg;
		this.interval = interval;
	}
	
	@Override
	public void fire() {		
		node.broadcast(msg);
		if (interval > 0){
			FloodFindDsdv message = (FloodFindDsdv)msg;
			BaseStation bs = (BaseStation)node;
			message.sequenceID = bs.getSequenceID();
			this.startRelative(interval, node);
		}
	}

}
