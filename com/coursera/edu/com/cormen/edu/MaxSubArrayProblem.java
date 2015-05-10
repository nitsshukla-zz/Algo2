package com.cormen.edu;

public class MaxSubArrayProblem {

	public static void main(String[] args) {
		int[] arr = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		System.out.println(getMaxSub(arr,0,arr.length-1));
	}

	private static SubArr getMaxSub(int[] arr, int low, int high) {
		if(low==high)
			return new SubArr(low, high, arr[low]);
		int mid = (high+low)/2;
		SubArr leftSub = getMaxSub(arr, low, mid);
		SubArr riteSub = getMaxSub(arr, mid+1, high);
		SubArr crossSub = getCrossSub(arr, low, mid,high);
		if(leftSub.sum>riteSub.sum && leftSub.sum>crossSub.sum)
			return leftSub;
		if(leftSub.sum<riteSub.sum && riteSub.sum>crossSub.sum)
			return riteSub;
		return crossSub;
	}

	private static SubArr getCrossSub(int[] arr, int low, int mid, int high) {
		int leftSum = Integer.MIN_VALUE,leftPos=mid,sum=0;
		for(int i=mid;i>=0;i--){
			sum+=arr[i];
			if(sum>leftSum){
				leftPos=i;leftSum=sum;
			}
		}

		int riteSum = Integer.MIN_VALUE,ritePos=mid;sum=0;
		for(int i=mid+1;i<=high;i++){
			sum+=arr[i];
			if(sum>riteSum){
				ritePos=i;riteSum=sum;
			}
		}
		return new SubArr(leftPos, ritePos, riteSum+leftSum);
	}

}

class SubArr{
	int leftPos, rightPos, sum;

	public SubArr(int leftPos, int rightPos, int sum) {
		this.leftPos = leftPos;
		this.rightPos = rightPos;
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "SubArr [leftPos=" + (leftPos+1) + ", rightPos=" + (rightPos+1)
				+ ", sum=" + sum + "]";
	}


}