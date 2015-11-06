import java.io.*;
import java.util.*;

public class projectileproject{

	protected double launchspeed,launchangle,startingheight,flighttime,velocity,maxheight,distancetravel,endangle;
	
	public projectileproject (){
		launchspeed = 0;
		launchangle = 0;
		startingheight = 0;
		flighttime = 0;
		velocity = 0;
		maxheight = 0;
		distancetravel = 0;
		endangle = 0;
	}
	
	public projectileproject(projectileproject x){
		launchspeed  = x.getspeed();
		launchangle = x.getangle();
		startingheight = x.getheight();
		flighttime = x.flighttime();
		velocity = x.getvelocity();
		maxheight = x.getmax();
		distancetravel = x.getdistance();
		endangle = x.getend();
	}
	
	public double getspeed(){
		return launchspeed;
	}
	
	public double getangle(){
		return launchangle;
	}
	
	public double getheight(){
		return startingheight;
	}
	
	public double getflight(){
		return flighttime;
	}
	
	public double getvelocity(){
		return velocity;
	}
	
	public double getmax(){
		return maxheight;
	}
	
	public double getdistance(){
		return distancetravel;
	}
	
	public double getend(){
		return endangle;
	}
	
	public double[] inputnumbers(){
		Scanner keys= new Scanner(System.in);
		System.out.println("Do you want to input the range of numbers manually? (Type Y/N)");
		String ans= keys.next();
		if(ans.equalsIgnoreCase("Y")){
			double[] x= new double[6];
			Scanner inp = new Scanner(System.in);
			System.out.println("Set height range by entering each number followed by enter.");
			x [0]= inp.nextDouble();
			x [1]= inp.nextDouble();
			System.out.println("Set speed range by entering each number followed by enter.");
			x [2]= inp.nextDouble();
			x [3]= inp.nextDouble();
			System.out.println("Set shooting range by entering each number followed by enter. Don't exceed 90 for each number.");
			x [4]= inp.nextDouble();
			x [5]= inp.nextDouble();
			return x;
		}
		else{
			double[] x= new double[6];
			x [0]= (Math.random()*80)+1;
			x [1]= (Math.random()*80)+1;
			x [2]= (Math.random()*80)+1;
			x [3]= (Math.random()*80)+1;
			x [4]= (Math.random()*80)+1;
			x [5]= (Math.random()*80)+1;
			return x;
		}
	}
	
	public double flighttime(){
		double upward = Math.sin(this.getangle() * Math.PI / 180.0) * this.getspeed(); 
		double uptime = upward/9.81;
		double downward = upward + this.getheight();
		double downtime = Math.sqrt(downward*2/9.81);
		return downtime + uptime;
	}

	public void findvelocity(int currenttime){
		double xvelocity = Math.cos(this.getangle() * Math.PI / 180.0) * this.getspeed();
		double yinitial = Math.sin(this.getangle() * Math.PI / 180.0) * this.getspeed();
		double yvelocity = yinitial + -9.81 * (double)currenttime;
		double blah = Math.pow(yvelocity,2) + Math.pow(xvelocity,2);
		velocity = Math.sqrt(blah);
	}
	
	public double findxvelocity(){
		return Math.cos(this.getangle() * Math.PI / 180.0) * this.getspeed();
	}
	
	public double findyvelocity(){
		return Math.sin(this.getangle() * Math.PI / 180.0) * this.getspeed();
	}
	
	public double endyvelocity(){
		return (Math.sin(this.getangle() * Math.PI / 180.0) * this.getspeed() * this.flighttime() + 0.5 * -9.81 * Math.pow(this.flighttime(),2));
	}
	
	public void endangle(double finaltime){	
		double x = this.findxvelocity();
		double y = this.endyvelocity();
		endangle = Math.pow(Math.tan((double)(y/x)),-1.0);
	}
	
	public void maxheight(){
		double upward = Math.sin(this.getangle() * Math.PI / 180.0) * this.getspeed();
		maxheight = this.getheight() + upward;
	}
	
	public void distancetravel(double flighttime){
		double xvelocity = Math.cos(this.getangle() * Math.PI / 180.0) * this.getspeed();
		double ytravel = this.getheight();
		double xtravel = xvelocity * flighttime;
		distancetravel = Math.sqrt(Math.pow(ytravel,2.0) + Math.pow(xtravel,2.0));
	}
	
	public static double truncate(double x){
		x=x*100;
		x=(int)x;
		x=x/100;
		return x;
	}
	
	public void frq() throws IOException{
	
		Scanner inp = new Scanner(System.in);
		
		System.out.print("Number of flight time problems: ");
		int problem = inp.nextInt();
		System.out.print("Number of instantaneous velocity problems: ");
		int problem2 = inp.nextInt();
		System.out.print("Number of max height problems: ");
		int problem3 = inp.nextInt();
		System.out.print("Number of total displacement problems: ");
		int problem4 = inp.nextInt();
		System.out.print("Number of angle upon landing problems: ");
		int problem5 = inp.nextInt();
		double x[]= this.inputnumbers();
		int counter= 1;
		int counter2= 1;
		int counter3= 1;
		int counter4= 1;
		int counter5= 1;
		
		while(problem>0){

			double f= (Math.random()*(x[1]-x[0]) +x[0]);
			f=truncate(f);
			double g= (Math.random()*(x[3]-x[2]) +x[2]);
			g=truncate(g);
			double h= (Math.random()*(x[5]-x[4]) +x[4]);
			h=truncate(h);
			double b= g*Math.sin(Math.PI*h/180);
			double Answer = ((-b-Math.pow(Math.pow(b,2)+4*(4.9)*(f),.5))/(-9.8));
			Answer=truncate(Answer);
			
			System.out.println("FRQ Flight Time Problem "+ counter +" : Fred Flintstone throws a hard rock from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. How long does the hard rock stay in the air?"); 
			double guess = inp.nextDouble();
			String a= null;
			
			loop1:
			while(guess!=Answer){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.nextDouble();
			}
			
			if(guess==Answer){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is "+Answer+" seconds.");
			problem--;
			counter++;
		}
		
		while(problem2>0){
	
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			int w = (int)(Math.random()*(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8))));
			this.findvelocity(w);
			double b = this.getvelocity();
			double Answer= (1000*Math.pow(Math.pow(g*Math.cos(Math.PI*h/180),2)+Math.pow(g*Math.sin(Math.PI*h/180) -9.8*w,2),.5))/1000;
			Answer=truncate(Answer);
			
			System.out.println("FRQ Instantaneous Velocity Problem "+ counter2 +" : MacGyver throws his ball of yarn from a height of " + f + " meters at a speed of " + g + " m/s with an angle of " + h + " degrees from the positive x-axis. At " + w + " seconds, what is the speed of the yarn?");
			
			double guess = inp.nextDouble();
			String a= null;
			
			loop1:
			while(guess!=Answer){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.nextDouble();
			}
			
			if(guess==Answer){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is "+Answer+" meters/second");

			problem2--;
			counter2++;
		}
		while(problem3>0){
		
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double Answer= (Math.pow(g*Math.sin(Math.PI*h/180),2)/19.6)+f;
			Answer=truncate(Answer);

			System.out.println("FRQ Max Height Problem "+ counter3 +" : Rafael Nadal hits a tennis ball from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. What is the greatest height attained by the ball?"); 
			
			double guess = inp.nextDouble();
			String a= null;
			
			loop1:
			while(guess!=Answer){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.nextDouble();
			}
			
			if(guess==Answer){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is "+Answer+" meters");

			problem3--;
			counter3++;
		}
		
		while(problem4>0){
		
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			double time=(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8)));
			double Answer= g*Math.cos(Math.PI*h/180)*time;
			Answer=truncate(Answer);

			System.out.println("FRQ Total Displacement Problem "+ counter4 +" : James Bond shoots a gun from a height of " + f + " meters, releasing a bullet at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. Upon hitting the ground, how far is the bullet from the original location?"); 

			double guess = inp.nextDouble();
			String a= null;
			
			loop1:
			while(guess!=Answer){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.nextDouble();
			}
			
			if(guess==Answer){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is "+Answer+" meters");

			problem4--;
			counter4++;
		}
		while(problem5>0){
	
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			double time=(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8)));
			double yvelocity= -q+9.8*time;
			double Answer= (180/Math.PI)*Math.atan(yvelocity/(g*Math.cos(Math.PI*h/180)));
			Answer=truncate(Answer);
			
			System.out.println("FRQ Angle Upon Landing Problem "+ counter5 +" : Team Rocket throws Meowth from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. Upon hitting the ground, what angle does Meowth make with the surface?"); 
			
			double guess = inp.nextDouble();
			String a= null;
			
			loop1:
			while(guess!=Answer){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.nextDouble();
			}
			
			if(guess==Answer){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is "+Answer+" degrees");
			
			problem5--;
			counter5++;
		}
	}
	
	public void multiplechoice() throws IOException{
	
		String[] letters = new String[] {"A","B","C","D","E"};
		double[] Answers = new double[5];
		
		Scanner inp = new Scanner(System.in);
		System.out.print("Number of flight time problems: ");
		int problem = inp.nextInt();
		System.out.print("Number of instantaneous velocity problems: ");
		int problem2 = inp.nextInt();
		System.out.print("Number of max height problems: ");
		int problem3 = inp.nextInt();
		System.out.print("Number of total displacement problems: ");
		int problem4 = inp.nextInt();
		System.out.print("Number of angle upon landing problems: ");
		int problem5 = inp.nextInt();
		double x[]= this.inputnumbers();
		int counter= 1;
		int counter2= 1;
		int counter3= 1;
		int counter4= 1;
		int counter5= 1;
		
		while(problem>0){
		
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double b= g*Math.sin(Math.PI*h/180);
			for(int i=0; i<5; i++){
				Answers[i] = ((1000 * ((-b-Math.pow(Math.pow(b,2)+4*(4.9)*(f),.5))/(-9.8*Math.sin(i+1)))/1000));
				Answers[i]=truncate(Answers[i]);
			}
			int ee= (int)(Math.random()*5);
			Answers[ee] = ((1000 * ((-b-Math.pow(Math.pow(b,2)+4*(4.9)*(f),.5))/(-9.8)))/1000);
			Answers[ee]=truncate(Answers[ee]);

			System.out.println("Multiple Choice Flight Time Problem "+ counter +" : Fred Flintstone throws a hard rock from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. How long does the hard rock stay in the air?"); 
			
			for(int i = 0; i < 5; i++){
				System.out.println(letters[i] + " : " + Answers[i] + " seconds");

			}
			
			System.out.println("Enter the letter of the answer choice you wish to choose.");
			
			String guess = inp.next();
			String a= null;
			
			loop1:
			while(!guess.equalsIgnoreCase(letters[ee])){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer choices again? (Type Yes/No)");
				a= inp.next();
				if(a.equalsIgnoreCase("Yes")){
					for(int i = 0; i < 5; i++){
						System.out.println(letters[i] + " : " + Answers[i] + " seconds");
					}
				}
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.next();
			}
			
			if(guess.equalsIgnoreCase(letters[ee])){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is " + letters[ee] + " : " + Answers[ee] + " seconds");

			problem--;
			counter++;
		}
		while(problem2>0){
	
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			int w = (int)(Math.random()*(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8))));
			this.findvelocity(w);
			int bb= (int)(Math.random()*5);
			for(int i=0; i<5; i++){
				Answers[i] = (1000*Math.pow(Math.pow(g*Math.cos(Math.PI*h/180),2)+Math.pow(g*Math.sin(Math.PI*h/180+((i+1)*5)) -9.8*w,2),.5))/1000;
				Answers[i]=truncate(Answers[i]);
			}
			Answers[bb] = (1000*Math.pow(Math.pow(g*Math.cos(Math.PI*h/180),2)+Math.pow(g*Math.sin(Math.PI*h/180) -9.8*w,2),.5))/1000;
			Answers[bb]=truncate(Answers[bb]);
	
			System.out.println("Multiple Choice Instantaneous Velocity Problem "+ counter2 +" : MacGyver throws his ball of yarn from a height of " + f + " meters at a speed of " + g + " m/s with an angle of " + h + " degrees from the positive x-axis. At " + w + " seconds, what is the speed of the yarn?");
			
			for(int i = 0; i < 5; i++){
				System.out.println(letters[i] + " " + Answers[i] + " meters/second");

			}
			
			System.out.println("Enter the letter of the answer choice you wish to choose.");
			
			String guess = inp.next();
			String a= null;
			
			loop1:
			while(!guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer choices again? (Type Yes/No)");
				a= inp.next();
				if(a.equalsIgnoreCase("Yes")){
					for(int i = 0; i < 5; i++){
						System.out.println(letters[i] + " : " + Answers[i] + " meters/seconds");
					}
				}
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.next();
			}
			
			if(guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is " + letters[bb] + " : " + Answers[bb] + " meters/seconds");

			problem2--;
			counter2++;
		}
		
		while(problem3>0){

			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			int bb= (int)(Math.random()*5);
			for(int i=0; i<5; i++){
				Answers[i] = (Math.pow(g*Math.sin(Math.PI*h/180+((i+1)*5)),2)/19.6)+f;
				Answers[i]=truncate(Answers[i]);
			}
			Answers[bb] = (Math.pow(g*Math.sin(Math.PI*h/180),2)/19.6)+f;
			Answers[bb]=truncate(Answers[bb]);
			
			System.out.println("Multiple Choice Max Height Problem "+ counter3 +" : Rafael Nadal hits a tennis ball from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. What is the greatest height attained by the ball?"); 
			
			for(int i = 0; i < 5; i++){
				System.out.println(letters[i] + " : " + Answers[i] + " meters");
			}
			
			System.out.println("Enter the letter of the answer choice you wish to choose.");
			
			String guess = inp.next();
			String a= null;
			
			loop1:
			while(!guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer choices again? (Type Yes/No)");
				a= inp.next();
				if(a.equalsIgnoreCase("Yes")){
					for(int i = 0; i < 5; i++){
						System.out.println(letters[i] + " : " + Answers[i] + " meters");
					}
				}
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.next();
			}
			
			if(guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is " + letters[bb] + " : " + Answers[bb] + " meters");
			
			problem3--;
			counter3++;
		}
		while(problem4>0){

			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			double time=(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8)));
			int bb= (int)(Math.random()*5);
			for(int i=0; i<5; i++){
				Answers[i] = g*Math.cos(Math.PI*h/180+((i+1)*5))*time;
				Answers[i]=truncate(Answers[i]);
			}
			Answers[bb] = g*Math.cos(Math.PI*h/180)*time;
			Answers[bb]=truncate(Answers[bb]);
			
			System.out.println("Multiple Choice Total Displacement Problem "+ counter4 +" : James Bond shoots a gun from a height of " + f + " meters, releasing a bullet at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. Upon hitting the ground, how far is the bullet from the original location?"); 
			
			for(int i = 0; i < 5; i++){
				System.out.println(letters[i] + " : " + Answers[i] + " meters");
			}
			
			System.out.println("Enter the letter of the answer choice you wish to choose.");
			
			String guess = inp.next();
			String a= null;
			
			loop1:
			while(!guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer choices again? (Type Yes/No)");
				a= inp.next();
				if(a.equalsIgnoreCase("Yes")){
					for(int i = 0; i < 5; i++){
						System.out.println(letters[i] + " : " + Answers[i] + " meters");
					}
				}
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.next();
			}
			
			if(guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is " + letters[bb] + " : " + Answers[bb] + " meters");

			problem4--;
			counter4++;
		}
		while(problem5>0){
		
			double f= (1000*(Math.random()*(x[1]-x[0]) +x[0]))/1000;
			f=truncate(f);
			double g= (1000*(Math.random()*(x[3]-x[2]) +x[2]))/1000;
			g=truncate(g);
			double h= (1000*(Math.random()*(x[5]-x[4]) +x[4]))/1000;
			h=truncate(h);
			double q= g*Math.sin(Math.PI*h/180);
			double time=(Math.random()*(((-q-Math.pow(Math.pow(q,2)+4*(4.9)*(f),.5))/(-9.8))));
			double yvelocity= -q+9.8*time;
			int bb= (int)(Math.random()*5);
			for(int i=0; i<5; i++){
				Answers[i] = (180/Math.PI)*Math.atan((yvelocity+((i+1)*2))/(g*Math.cos(Math.PI*h/180)));
				Answers[i]=truncate(Answers[i]);
			}
			Answers[bb] = Math.atan(yvelocity/(g*Math.cos(Math.PI*h/180)));
			Answers[bb]=truncate(Answers[bb]);
			
			System.out.println("Multiple Choice Angle Upon Landing Problem "+ counter5 +" : Team Rocket throws Meowth from a height of " + f + " meters at a speed of " + g + " meters/second with an angle of " + h + " degrees from the positive x-axis. Upon hitting the ground, what angle does Meowth make with the surface?");
			
			for(int i = 0; i < 5; i++){
				System.out.println(letters[i] + " : " + Answers[i] + " degrees");
			}
			
			System.out.println("Enter the letter of the answer choice you wish to choose.");
			
			String guess = inp.next();
			String a= null;
			
			loop1:
			while(!guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Not the right answer.");
				System.out.println("Do you want the answer choices again? (Type Yes/No)");
				a= inp.next();
				if(a.equalsIgnoreCase("Yes")){
					for(int i = 0; i < 5; i++){
						System.out.println(letters[i] + " : " + Answers[i] + " degrees");
					}
				}
				System.out.println("Do you want the answer? (Type Y/N)");
				a= inp.next();
				if(a.equalsIgnoreCase("Y")){
					break loop1;
				}
				System.out.println("Then try again.");
				guess = inp.next();
			}
			
			if(guess.equalsIgnoreCase(letters[bb])){
				System.out.println("Correct! Great Job!");
			}
			
			System.out.println("The answer is " + letters[bb] + " : " + Answers[bb] + " degrees");
			
			problem5--;
			counter5++;
		}	
	}
	public static void main(String[] args) throws IOException{
		Scanner x = new Scanner(System.in);
		System.out.println("Welcome to the Physics Projectile Problem Generator by Harry Liu.");
		projectileproject good = new projectileproject();
		System.out.println("Take your pick: ");
		System.out.println("Type 1 for Multiple Choice");
		System.out.println("Type 2 for Free Response Questions");
		
		int a = x.nextInt();
		if(a == 2){
			good.frq();
		}
		if(a == 1){
			good.multiplechoice();	
		}
		System.out.println("Thank you for using the Physics Projectile Problem Generator!");
	}
}