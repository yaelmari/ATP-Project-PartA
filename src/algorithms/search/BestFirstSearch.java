package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{




    @Override
    public String getName() {
        return "Best";
    }


    @Override
    /**
     * this function overides the inQueue of BFS and in this function we add a cost by the distance of the Astates,
     * if the distance is 1 so the are near each other otherwise they are obliquely.
     */
    protected void inQueue(AState aState, AState parent){
        if(parent == null){
            queue.add(aState);
            return;
        }

        else if (aState.getPos().getDistance(parent.getPos()) == 1) {
            aState.setCost(parent.getCost() + 10);
        }
        else{
            aState.setCost(parent.getCost() + 15);
        }
        queue.add(aState);



    }

    @Override
    protected AState deQueue(){
        return queue.poll();

    }
}
