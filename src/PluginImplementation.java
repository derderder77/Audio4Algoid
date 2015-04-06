
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import fr.cyann.al.ast.Block;
import fr.cyann.al.ast.declaration.ObjectDeclaration;
import fr.cyann.al.factory.FactoryUtils;
import fr.cyann.al.factory.TypeNameFunctionMap;
import fr.cyann.al.visitor.RuntimeContext;
import fr.cyann.algoid.plugin.ApplicationContext;
import fr.cyann.algoid.plugin.Plugin;
import fr.cyann.algoid.plugin.PluginView;
import fr.cyann.algoid.tools.Logger;
import fr.cyann.jasi.builder.ASTBuilder;


public class PluginImplementation implements Plugin
{
	
	static final String VERSION = "1" ;
	static final String DEFAULT_AUDIO_PATH = "/sdcard/Algoid/raw/" ;
	MediaPlayer mediaPlayer = null ;
	boolean loaded = false ;
	
	@Override
	public void addFrameworkObjects(ApplicationContext ac, ASTBuilder builder)
	{
		ObjectDeclaration<RuntimeContext> audio = FactoryUtils.addObject(builder,"audio") ;
				FactoryUtils.addMethod(audio, "playSound", new FactoryUtils.Behaviour()  {

				@Override
				public void visite(Block<RuntimeContext> block, RuntimeContext context)
				{
				String param = FactoryUtils.getParam(block,0).getString(context) ;
				String path ;
				if ( param.charAt(0) == '/' ) {
					path = param ;
				}
				else {
					path = DEFAULT_AUDIO_PATH+param ;
				}
				SoundPool soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,1) ;
				soundPool.load(path,1) ;
					soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
							public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
								soundPool.play(sampleId,1f,1f,1,0,1) ;
							}
						});				
				}
			
		},"path") ;
		FactoryUtils.addMethod(audio, "loadMusic", new FactoryUtils.Behaviour() {

				@Override
				public void visite(Block<RuntimeContext> p1, RuntimeContext p2)
				{
					MediaPlayer mp = new MediaPlayer() ;
					String param = FactoryUtils.getParam(p1,0).getString(p2) ;
					String path ;
					if ( param.charAt(0) == '/' ) {
						path = param ;
					}
					else {
						path = DEFAULT_AUDIO_PATH+param ;
					}
					try
					{
						mp.setDataSource(path) ;
						mp.prepare() ;
						loaded = true ;
					}
					catch (SecurityException e)
					{}
					catch (IllegalArgumentException e)
					{
						Logger.write(Logger.Severity.ERROR,"File "+path+" is not an audio file") ;
					}
					catch (IllegalStateException e)
					{}
					catch (IOException e)
					{
						Logger.write(Logger.Severity.ERROR,"File "+path+" do not exist or is not readable") ;
					}
					mediaPlayer = mp ;					
				}

			
		},"path") ;
		
		FactoryUtils.addMethod(audio, "playMusic", new FactoryUtils.Behaviour() {

				@Override
				public void visite(Block<RuntimeContext> p1, RuntimeContext p2)
				{
					if ( loaded ) {
					mediaPlayer.start() ;
					}
					else {
						Logger.write(Logger.Severity.ERROR,"You must load a music before play") ;
					}
				}

			
		}) ;
		FactoryUtils.addMethod(audio, "stopMusic", new FactoryUtils.Behaviour() {

				@Override
				public void visite(Block<RuntimeContext> p1, RuntimeContext p2)
				{
					if ( mediaPlayer.isPlaying() ) {
						mediaPlayer.stop() ;
					}
				}

			
		}) ;
		FactoryUtils.addMethod(audio, "pauseMusic", new FactoryUtils.Behaviour() {

				@Override
				public void visite(Block<RuntimeContext> p1, RuntimeContext p2)
				{
					if ( mediaPlayer.isPlaying() ) {
						mediaPlayer.pause() ;
					}
				}

			
		}) ;
		
	}	
	
	@Override
	public void initialize(Context p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void addDynamicMethods(RuntimeContext p1, TypeNameFunctionMap p2)
	{
		// TODO: Implement this method
	}

	

	@Override
	public PluginView getAdditionalView()
	{
		// TODO: Implement this method
		return null;
	}

	
	

	@Override
	public void removeListeners()
	{
		// TODO: Implement this method
	}
	
	@Override
	public String getAuthor()
	{
		return "derderder" ;
	}

	@Override
	public Date getCreationDate()
	{
		Calendar calendar = Calendar.getInstance() ;
		calendar.set(2015,04,05) ;
		return calendar.getTime() ;
	}

	@Override
	public String getName()
	{
		return "SoundPlugin - Audio Gesture for Algoid" ;
	}

	@Override
	public String getVersion()
	{
		return VERSION;
	}
	

}
