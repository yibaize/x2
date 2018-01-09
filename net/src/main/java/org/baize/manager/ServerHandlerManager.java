package org.baize.manager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.baize.message.TcpHandler;
import org.baize.session.ISession;
import org.baize.session.SessionImpl;

import javax.sound.midi.Soundbank;

public class ServerHandlerManager extends SimpleChannelInboundHandler<Request>{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//客户端在
		System.err.println("用户上线"+ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("用户下线"+ctx.channel().remoteAddress());
		outLine(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("用户下线"+ctx.channel().remoteAddress());
		outLine(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		//将消息发送到消息分发
		handlerMessage(ctx.channel(),request);
	}
	/**消息分发器*/
	private void handlerMessage(Channel channel, Request request){
		ISession session = new SessionImpl(channel);
		TcpHandler.messageRecieve(session,request);
	}
	private void outLine(Channel channel){
		ISession session = new SessionImpl(channel);
		Msg m = new Msg("10");
		TcpHandler.messageRecieve(session,new Request((short)5,m));
	}

	/**
	 * 心跳机制
	 * @param ctx
	 * @param evt
	 * @throws Exception
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent e = (IdleStateEvent) evt;
//			if(e.state().equals(IdleState.READER_IDLE)){
//				System.out.println("---读空闲---");
//				ctx.channel().close();
//			}else if(e.state().equals(IdleState.WRITER_IDLE)){
//				System.out.println("---写空闲----");
//			}else
			 if(e.state().equals(IdleState.ALL_IDLE)){
				System.out.println("---读写空闲---");
			 	outLine(ctx.channel());
//				ctx.channel().writeAndFlush("ping\r\n");
			}
		}
	}
}
