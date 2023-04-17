package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{




    @Override
    public String getName() {
        return "Best";
    }


    @Override
    protected void inQueue(AState aState, AState parent){
        if(parent == null){
            queue.add(aState);
            return;
        }

        else if (aState.getPos().getDistance(parent.getPos()) == 1) {
            aState.setCost(parent.getCost() + 10);
        }
        else if(aState.getPos().getDistance(parent.getPos()) == 2){
            aState.setCost(parent.getCost() + 15);
        }
        queue.add(aState);



    }

    @Override
    protected AState deQueue(){
        return queue.poll();

    }
}
