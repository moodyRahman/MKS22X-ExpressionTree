public class ExpressionTree implements PrintableNode{



  /*return the expression as an infix notation string with parenthesis*/
  /* The sample tree would be: "(3 + (2 * 10))"     */
  public String toString(){
        /*you are to write this method*/
        return toStringh(this);
    }

    public String toStringh(ExpressionTree tree) {
        if(tree.isValue()) return "" + tree.getValue();
        return "( " + toStringh(tree.getLeft()) + " " + tree.getOp() + " " + toStringh(tree.getRight()) + " )";
    }


  /*return the expression as a postfix notation string without parenthesis*/
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
        /*you are to write this method*/
        return postfixh(this);
    }

    public String postfixh(ExpressionTree tree) {
        if(tree.isValue()) return "" + tree.getValue();
        return postfixh(tree.getLeft()) + " " + postfixh(tree.getRight()) + " " + tree.getOp();
    }

  /*return the expression as a prefix notation string without parenthesis*/
  /* The sample tree would be: "+ 3 * 2 10"     */

  public String toStringPrefix(){
      /*you are to write this method*/
      return prefixh(this);
  }

  public String prefixh(ExpressionTree tree) {
      if(tree.isValue()) return "" + tree.getValue();
      return tree.getOp() + " " + prefixh(tree.getLeft()) + " " + prefixh(tree.getRight());
  }


  /*return the value of the specified expression tree*/
  public double evaluate(){
    /*you are to write this method*/
    return evalHelp(this);
    }

  public double evalHelp(ExpressionTree tree){
	  if(tree.isValue()) {
		  return tree.getValue();
	  }
	  return apply(tree.getOp(), evalHelp(tree.getLeft()), evalHelp(tree.getRight()));
  }

  /*use the correct operator on both a and b, and return that value*/
  public double apply(char op, double a, double b){
	  if (op == '+'){
		  return a+b;
	  }
	  if (op == '-'){
		  return a-b;
	  }
	  if (op == '*'){
		  return a*b;
	  }
	  if (op == '/'){
		  return a/b;
	  }
	  return 0.0;
    }

    public String getText(){
      return Double.toString(value);
    }




  //If you are not able to take the exam Friday, speak to me in person tomorrow.
  ////////////////////ONLY EDIT ABOVE THIS LINE////////////////////



  public char op;
  public double value;
  public ExpressionTree left,right;

  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }

  public char getOp(){
    return op;
  }

  /* accessor method for Value, precondition is that isValue() is true.*/
  public double getValue(){
    return value;
  }
  /* accessor method for left, precondition is that isOp() is true.*/
  public ExpressionTree getLeft(){
    return left;
  }
  /* accessor method for right, precondition is that isOp() is true.*/
  public ExpressionTree getRight(){
    return right;
  }

  public boolean isOp(){
    return hasChildren();
  }
  public boolean isValue(){
    return !hasChildren();
  }

  public boolean hasChildren(){
    return left != null && right != null;
  }


  public static void main(String[] args){
    //ugly main sorry!
    ExpressionTree a = new ExpressionTree(4.0);
    ExpressionTree b = new ExpressionTree(2.0);

    ExpressionTree c = new ExpressionTree('+',a,b);
    System.out.println(c);
    System.out.println(c.toStringPostfix());
    System.out.println(c.toStringPrefix());
    System.out.println(c.evaluate());//6.0

    ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));
    System.out.println(d);
    System.out.println(d.toStringPostfix());
    System.out.println(d.toStringPrefix());
    System.out.println(d.evaluate());//21

    ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//20

    ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//21

    ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//10.5
  }
}
