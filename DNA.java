package asg_1;

import BasicIO.ASCIIDataFile;
/*
 * Class: DNA.Java
 * Project: Assignment_1
 * Author: Trevor Vanderee
 * Student ID: 5877022
 */
public class DNA {
	
	private Node crt;
	
	
	/**
	 * This program reads in data from a text file and 
	 * finds the gcContent and Reverse Complement of each
	 * given strand. It then generates all possible strands
	 * based on given criteria.
	 */
	public DNA( ){
		ASCIIDataFile in = new ASCIIDataFile();
		Node p;
		int numStrands = in.readInt();
		for(int i =0; i<(numStrands); i++){
			int numBases = in.readInt();
			Node strand = new Node(in.readString(),null);
			p= strand;
			for(int j =0; j<=numBases-2; j++){
				p.next = new Node(in.readString(),null);
				p=p.next;
			}
			System.out.println(printStrand(strand)+ "\t GC content: "+ gcContent(strand)+
							"\t Reverse Complement: "+ printStrand(reverseCompliment(strand)));
			reverseCompliment(strand);
			
			crt = new Node ("a",null);
		}
		int length = in.readInt();
		int gcc= in.readInt();
		generateStrands(length,0,gcc,crt);
	}//DNA
	
	/**
	 * This method takes a variety of parameters and creates
	 * 	a mock DNA strand in lexicographic order.
	 * @param int n: The Length of the desired strands.
	 * @param int k: The current position of the recursive calls.
	 * @param int gcc: The desired gcContent for each strand.
	 * @param Node x: The Node used for creating each strand.
	 */
	private void generateStrands(int n,int k, int gcc,Node x){
		if(!(k==n-1)){
			
			x.next = new Node("A",null);
			x.item = "A";
			generateStrands(n,k+1,gcc,x.next);			
			
			x.item ="C";
			generateStrands(n,k+1,gcc,x.next);			
			
			x.item="G";
			generateStrands(n,k+1,gcc,x.next);
			
			x.item="T";
			generateStrands(n,k+1,gcc,x.next);
		
		}else{
			x.item = "A";
			if(gcContent(crt)==gcc){
				if(!(compareStrands(crt,reverseCompliment(crt)))){
					
				}
			}
			x.item = "C";
			if(gcContent(crt)==gcc){
				if(!(compareStrands(crt,reverseCompliment(crt)))){
					System.out.println(printStrand(crt));
				}
			}
			x.item = "G";
			if(gcContent(crt)==gcc){
				if(!(compareStrands(crt,reverseCompliment(crt)))){
					System.out.println(printStrand(crt));
				}
			}
			x.item = "T";
			if(gcContent(crt)==gcc){
				if(!(compareStrands(crt,reverseCompliment(crt)))){
					System.out.println(printStrand(crt));
				}
			}
			x.item="A";
		}
		
	}//generateStrands
	
	/**
	 * This method takes a Strand and returns 
	 * 	its reverse complement.
	 * @param Node  n: The Node to be acted on.
	 * @return Node: The finished node.
	 */
	private Node reverseCompliment(Node n){
		Node r;
		Node p;
		if(!(n.next==null)){
			r = reverseCompliment(n.next);
			p=r;
			while(!(p.next==null)){
			p = p.next;
			}
			p.next = new Node(getCompliment(n.item),null);
		}else{
			r = new Node(getCompliment(n.item),null);
		}
		return r;
	}//reverseCompliment

	/**
	 * This method takes the strand and returns the amount
	 * 	of g's and c's found in the strand.
	 * @param Node n: The Strand to be examined.
	 * @return int: The number of g's and c's in the strand. 
	 */
	private int gcContent(Node n){
		int l =0;
		if(!(n==null)){
			
			l = gcContent(n.next);
			if (n.item.equals("G")||n.item.equals("C")){
				return ++l;
			}else {
				return l;
			}
		}
		return l;
	}//gcContent
	
	/**
	 * This method takes a String and finds its DNA
	 * 	complement and returns the complement.
	 * @param String s: the input to be complemented.
	 * @return String out: the complement of s.
	 */
	private String getCompliment(String s){
		String out;
		if(s.equals("A")){
			out = "T";
		}else if(s.equals("T")){
			out = "A";
		}else if(s.equals("G")){
			out = "C";
		}else if(s.equals("C")){			
			out = "G";
		}else{
			out =s;
		}
		return out;
	}//getCompliment
	
	/**
	 * This Method takes the data from the Nodes and adds
	 *  it to a String to be returned to the invoker.
	 * @param Node in: the strand to be Converted to string
	 * @return String to be printed
	 */
	private String printStrand(Node in){
		String out = "";
		Node p=in;
			while(!(p==null)){
				out += p.item;
				p=p.next;
			}
		return out;
	}//printStrand
	
	/**
	 * This method compares to strands and returns a true
	 * 	boolean if the strands are equivalent.
	 * @param Node a: A strand to be compared to strand b.
	 * @param Node b: A strand to be compared to strand a.
	 * @return boolean: returns true if strands are equivalent.
	 */
	private boolean compareStrands(Node a, Node b){
		boolean ret;
		if(a.next==null^b.next==null){
			return false;
		}
		if(a.next==null){
			if(a.item.equals(b.item)){
				return true;
			}else{
				return false;
			}
		}else{
			ret = compareStrands(a.next,b.next);
		}
		return ret;
	}//compareStrands
	
	public static void main(String[] args){@SuppressWarnings("unused")
		DNA d = new DNA( );}
}
