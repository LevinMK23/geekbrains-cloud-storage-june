package NettyServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MainHandler extends SimpleChannelInboundHandler<String> {

    static ConcurrentLinkedDeque<ChannelHandlerContext> clients = new ConcurrentLinkedDeque<>();
    static int cnt = 0;
    private String userName;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        cnt++;
        userName = "User#" + cnt;
        clients.add(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        cnt--;
        clients.remove(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
        s = s.replaceAll("\n+|\r+", "");
        String[] exp = s.split(" +");
        for (String e : exp) {
            System.out.println(e);
        }
        int left = Integer.parseInt(exp[0]);
        char op = exp[1].charAt(0);
        int right = Integer.parseInt(exp[2]);
        int result = 0;
        switch (op) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
            case '/':
                result = left / right;
                break;
        }
        for (ChannelHandlerContext context : clients) {
            context.writeAndFlush(String.valueOf(result));
        }
    }
}
