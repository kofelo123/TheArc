<!-- ///배경관련  -->
<link rel="stylesheet" href="/thearc/resources/bootstrap/css/vegas.min.css">
<script src="/thearc/resources/bootstrap/js/jquery-2.1.3.min.js"></script>
<script src="/thearc/resources/bootstrap/js/vegas.min.js"></script>

<script>
    $(function(){
        $('body').vegas({
            slides:[

                {
                    video : {
                        src: ['/thearc/resources/bootstrap/dew.webm'],
                        loop:false,
                        mute:true
                    }
                }
            ]
        });
    });
</script>