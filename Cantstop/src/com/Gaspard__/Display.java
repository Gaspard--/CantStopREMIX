package com.Gaspard__;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback.SAM;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

public class Display implements Runnable {
	private GLFWErrorCallback errorCallback;
	private long window;

	public Display() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try{
			glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
			if ( glfwInit() != GL11.GL_TRUE )throw new IllegalStateException("Unable to initialize GLFW");
			glfwWindowHint(GLFW_VISIBLE, GL11.GL_FALSE); // the window will stay hidden after creation
	        glfwWindowHint(GLFW_RESIZABLE, GL11.GL_TRUE); // the window will be resizable
	 
	        int WIDTH = 600;
	        int HEIGHT = 600;
	 
	        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL);
	        if ( window == MemoryUtil.NULL )
	            throw new RuntimeException("Failed to create the GLFW window");
	        // Get the resolution of the primary monitor
	        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
	        // Center our window
	        glfwSetWindowPos(
	            window,
	            (GLFWvidmode.width(vidmode) - WIDTH) / 2,
	            (GLFWvidmode.height(vidmode) - HEIGHT) / 2
	        );
	        GLFWWindowSizeCallback(new SAM() {
				
				@Override
				public void invoke(long window, int width, int height) {
					 glfwSetWindowPos(
					            window,
					            (GLFWvidmode.width(vidmode) - width) / 2,
					            (GLFWvidmode.height(vidmode) - height) / 2
					        );
				}
			});
	        // Make the OpenGL context current
	        glfwMakeContextCurrent(window);
	        // Enable v-sync
	        glfwSwapInterval(1);
	 
	        // Make the window visible
	        glfwShowWindow(window);
	        GLContext.createFromCurrent();
	        
	        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	        
	        while ( glfwWindowShouldClose(window) == GL11.GL_FALSE ) {
	        	
	            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	 
	            glfwSwapBuffers(window); // swap the color buffers
	 
	            // Poll for window events. The key callback above will only be
	            // invoked during this call.
	            glfwPollEvents();
	        }
	        
		}
		finally{
			glfwTerminate();
            errorCallback.release();
		}
	}
	
}
