/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameInfoComponent } from 'app/entities/realname-info/realname-info.component';
import { RealnameInfoService } from 'app/entities/realname-info/realname-info.service';
import { RealnameInfo } from 'app/shared/model/realname-info.model';

describe('Component Tests', () => {
    describe('RealnameInfo Management Component', () => {
        let comp: RealnameInfoComponent;
        let fixture: ComponentFixture<RealnameInfoComponent>;
        let service: RealnameInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameInfoComponent],
                providers: []
            })
                .overrideTemplate(RealnameInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RealnameInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RealnameInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.realnameInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
