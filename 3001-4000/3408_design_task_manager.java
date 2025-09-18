/*------------Sorted Set Solution */
class TaskManager {

    class Task {
        int userId, id, priority;
        Task(int u, int i, int p) {
            userId = u;
            id = i;
            priority = p;
        }
    }

    Map<Integer, Task> tasksMap; // taskid --> task
    TreeSet<Task> tasksSet; // sorted collection of all the tasks on the basis of priority

    public TaskManager(List<List<Integer>> tasks) {
        // nlogn
        tasksMap = new HashMap<>();
        tasksSet = new TreeSet<>((a, b) -> {
            if (a.priority == b.priority) return b.id - a.id;
            return b.priority - a.priority;
        });
        for (List<Integer> t : tasks) {
            int user = t.get(0);
            int id = t.get(1);
            int pr = t.get(2);
            Task task = new Task(user, id, pr);
            tasksMap.put(id, task);
            tasksSet.add(task);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        // logn
        Task task = new Task(userId, taskId, priority);
        tasksMap.put(taskId, task);
        tasksSet.add(task);
    }
    
    public void edit(int taskId, int newPriority) {
        // O(logn)
        Task old = tasksMap.get(taskId);
        tasksSet.remove(old);
        Task updated = new Task(old.userId, old.id, newPriority);
        tasksMap.put(taskId, updated);
        tasksSet.add(updated);
    }
    
    public void rmv(int taskId) {
        // O(logn)
        Task curr = tasksMap.get(taskId);
        tasksMap.remove(taskId);
        tasksSet.remove(curr);
    }
    
    public int execTop() {
        // O(logn)
        if (tasksSet.isEmpty()) return -1;
        Task top = tasksSet.pollFirst();
        //[5 -- 10 -- 15 -- 20]
        tasksMap.remove(top.id);
        return top.userId;
    }
}

/**

UserID           2              4   5
TaskID           102            104 105
Priority        20->8           5   15

output: 3, 5

// sorted order on the basis of priority - TreeSet
// Map, TaskID --> Task[userid, taskid, priority]



 */

 /*------------Priority Queue Solution---------------- */
 class TaskManager {

    Map<Integer, Task> taskMap;
    PriorityQueue<Task> taskQueue; 

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        taskQueue = new PriorityQueue<>((a,b)-> {
            if(a.priority==b.priority) {
                return b.taskid-a.taskid;
            }
            else return b.priority - a.priority;
        });
        for(List<Integer> task : tasks) {
            int userid = task.get(0);
            int taskid = task.get(1);
            int priority = task.get(2);

            Task obj = new Task(userid, taskid, priority);
            taskMap.put(taskid, obj); // we now taskids are unique
            taskQueue.offer(obj);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Task obj = new Task(userId, taskId, priority);
        taskMap.put(taskId, obj); // we now taskids are unique
        taskQueue.offer(obj);
    }
    
    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        if(old!=null) {
            old.removed = true;
            Task updated = new Task(old.userid, old.taskid, newPriority);

            // Put in map and queue
            taskMap.put(taskId, updated);
            taskQueue.offer(updated);
        }

        // Create a fresh Task object with new priority
        
    }
    
    public void rmv(int taskId) {
        Task data = taskMap.get(taskId);

        if(data!=null) {
            data.removed = true;
            taskMap.remove(taskId);
        }
        
        
    }
    
    public int execTop() {
        while (!taskQueue.isEmpty()) {
            Task curr = taskQueue.peek();
            if(!curr.removed) {
                taskQueue.poll();
                taskMap.remove(curr.taskid);
                // curr.removed = true;
                return curr.userid;
            }
            else taskQueue.poll();
        }
        return -1;
    }

    class Task {
        int userid;
        int taskid;
        int priority;
        boolean removed;

        Task(int userid, int taskid, int priority) {
            this.userid = userid;
            this.taskid = taskid;
            this.priority = priority;
            removed = false;
        }
    }
}
