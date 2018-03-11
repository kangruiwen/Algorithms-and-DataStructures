package com.kang.dataStructures.graph;
/**
 * 功能：图的接口
 * @user krw
 * 2018年3月10日
 */
public interface Graph {
	public int V();
    public int E();
    public void addEdge( int v , int w );
    boolean hasEdge( int v , int w );
    void show();
    public Iterable<Integer> adj(int v);
}
