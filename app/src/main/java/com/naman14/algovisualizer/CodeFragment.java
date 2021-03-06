/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naman14.algovisualizer.algorithm.Algorithm;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class CodeFragment extends Fragment {

    LinearLayout codeLayout;

    public static CodeFragment newInstance(String algorithm) {
        CodeFragment fragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Algorithm.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_code, container, false);

        codeLayout = (LinearLayout) rootView.findViewById(R.id.codeLayout);

        setCode(getArguments().getString(Algorithm.KEY_ALGORITHM));

        return rootView;
    }

    public void setCode(String key) {
        if (codeLayout != null) {
            codeLayout.removeAllViews();
            switch (key) {
                case Algorithm.BUBBLE_SORT:
                    addCodeItem(AlgorithmCode.CODE_BUBBLE_SORT, "Bubble sort");
                    break;
                case Algorithm.INSERTION_SORT:
                    addCodeItem(AlgorithmCode.CODE_INSERTION_SORT, "Insertion sort");
                    break;
                case Algorithm.BST_SEARCH:
                    addCodeItem(AlgorithmCode.CODE_BST_SEARCH, "BST Search");
                    break;
                case Algorithm.BST_INSERT:
                    addCodeItem(AlgorithmCode.CODE_BST_INSERT, "BST Insert");
                    break;
                case Algorithm.BINARY_SEARCH:
                    addCodeItem(AlgorithmCode.CODE_BINARY_SEARCH, "Binary search");
                    break;
                case Algorithm.LINKED_LIST:
                    addCodeItem(AlgorithmCode.CODE_LINKED_LIST_INSERT, "Linked list insert data");
                    addCodeItem(AlgorithmCode.CODE_LINKED_LIST_DELETE, "Linked list delete node");
                    break;
                case Algorithm.STACK:
                    addCodeItem(AlgorithmCode.CODE_STACK_PUSH, "Stack push");
                    addCodeItem(AlgorithmCode.CODE_STACK_POP, "Stack pop");
                    addCodeItem(AlgorithmCode.CODE_STACK_PEEK, "Stack peek");
                    break;
                case Algorithm.BFS:
                    addCodeItem(AlgorithmCode.CODE_GRAPH_BFS, "Breadth first search");
                    break;
                case Algorithm.DFS:
                    addCodeItem(AlgorithmCode.CODE_GRAPH_DFS, "Depth first search");
                    break;
                case Algorithm.BELLMAN_FORD:
                    addCodeItem(AlgorithmCode.CODE_BELLMAN_FORD, "Bellman Ford");
                    break;
                case Algorithm.DIJKSTRA:
                    addCodeItem(AlgorithmCode.CODE_DIJKSTRA, "Dijkstra");
                    break;
            }
        }

    }

    private void addCodeItem(String code, String title) {
        View codeitem = LayoutInflater.from(getActivity()).inflate(R.layout.item_code_view, codeLayout, false);

        CodeView codeView = (CodeView) codeitem.findViewById(R.id.code_view);
        TextView titleText = (TextView) codeitem.findViewById(R.id.title);

        titleText.setText(title);

        codeView.setTheme(CodeViewTheme.GITHUB);
        codeView.setHorizontalScrollBarEnabled(true);

        //Temp solution, block parent touch evenets
        codeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        codeView.showCode(code);

        codeLayout.addView(codeitem);

    }
}
