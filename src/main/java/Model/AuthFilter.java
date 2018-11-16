package Model;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(filterName="AuthFilter")
public class AuthFilter implements Filter {
    List<User> users = new ArrayList<User>();
    List<Post> posts = new ArrayList<>();
    User currentUser = null;

    @Override
    public void init(FilterConfig argo) throws ServletException {
        posts = new PostDB().getAllPosts();
        //users = new Userdb().getUserdbms();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(posts);
        PrintWriter out = resp.getWriter();
        boolean flag = false;
        //out.print("OneThing");
        if(username!=null) {
            //users = new Userdb().getUserdbms();
            //Userdb userdbm = new Userdb();
            //List<User> users = userdbm.getUserdbms();
            //System.out.println(posts.get(0).getUserdb().getUserdbms().get(0).getUsername()+" WOR");
/*            for(Post usrs:posts){
                List<User> usersm = usrs.getUserdb().getUserdbms();
                for(User um:usersm){
                    System.out.println(um.getUsername()+" is now");
                }
            }*/
            //for(Post usrs:posts){
                //List<User> users = usrs.ggetUserdb().getUserdbms();
            List<User> users = Userdb.getAllUsers();

            for (User usr : users) {
                    System.out.println(usr.getUsername() + " Ok");
                    //out.print(usr.getUsername() + " "+usr.getPassword());
                    if (username.equals(usr.getUsername()) && password.equals(usr.getPassword())) {

                        currentUser = usr;
                        filterChain.doFilter(req, resp);
                        flag = true;
                        break;
                    }
                }
            //}
        }
        if(!flag){

            out.print("wrong user name/password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login");
            dispatcher.forward(req,resp);



        }
    }

    @Override
    public void destroy() {

    }
}
